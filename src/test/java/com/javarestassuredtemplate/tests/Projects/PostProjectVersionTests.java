package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.PostProjectVersionRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class PostProjectVersionTests extends TestBase {
    PostProjectVersionRequest projectVersionRequest;

    @Test
    public void deveIncluirVersaoProjetoComSucesso() {
       SoftAssert softAssert = new SoftAssert();

       //Parâmetros
       String nameProject = "PROJETO TESTE 1";
       String name = "v2.0.0";
       String description = "Major new version";
       String released = "true";
       String obsolete = "false";
       String timestamp = "2020-02-20";
       int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

       //Fluxo
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nameProject);
        projectVersionRequest = new PostProjectVersionRequest(list.get(0));
        projectVersionRequest.setJsonBody(name, description, released, obsolete, timestamp);
        Response response = projectVersionRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
