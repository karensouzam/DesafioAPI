package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.DeleteProjectRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.annotation.NotThreadSafe;
import org.junit.runner.notification.RunListener.ThreadSafe;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class DeleteProjectTests extends TestBase {
    DeleteProjectRequest deleteProjectRequest;

    @Test
    public void deleteProjectComSucesso(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String nome = "PROJETO TESTE 1";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Busca Projeto
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nome);
        deleteProjectRequest = new DeleteProjectRequest(list.get(0));
        Response response = deleteProjectRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
    @Test /*Conferir*/
    public void deleteProjectInexistente(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_FORBIDDEN;

        deleteProjectRequest = new DeleteProjectRequest("1");
        Response response = deleteProjectRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
