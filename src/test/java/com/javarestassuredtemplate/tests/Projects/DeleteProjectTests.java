package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.DeleteProjectRequest;
import com.javarestassuredtemplate.requests.Projects.PostProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class DeleteProjectTests extends TestBase {
    DeleteProjectRequest deleteProjectRequest;
    PostProjectsRequest postProjectsRequest;

    @Test
    public void deleteProjectComSucesso(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String name = "Projeto Teste";
        String statusId = "10";
        String statusName = "development";
        String statusLabel = "development";
        String description = "Projeto inserido pelo método Post";
        String enabled = "true";
        String filePath = "/tmp/";
        String viewStateId = "10";
        String viewStateName = "public";
        String viewStateLabel = "public";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBody(name, statusId, statusName, statusLabel, description, enabled, filePath, viewStateId, viewStateName, viewStateLabel);
        Response response = postProjectsRequest.executeRequest();

        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(name);
        deleteProjectRequest = new DeleteProjectRequest(list.get(0));
        Response responseDelete = deleteProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDelete.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naoDeveApagarProjectInexistente(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_FORBIDDEN;

        //Fluxo
        deleteProjectRequest = new DeleteProjectRequest("9999");
        Response response = deleteProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naoDeveApagarProjectComValorInvalido(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        deleteProjectRequest = new DeleteProjectRequest("0");
        Response response = deleteProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
