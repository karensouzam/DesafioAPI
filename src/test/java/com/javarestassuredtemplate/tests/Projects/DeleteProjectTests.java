package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.DeleteProjectRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
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

        //Fluxo
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nome);
        deleteProjectRequest = new DeleteProjectRequest(list.get(0));
        Response response = deleteProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
