package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.GetOnlyProjectRequest;
import com.javarestassuredtemplate.requests.Projects.PostProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetOnlyProjectTests extends TestBase {
    GetOnlyProjectRequest getOnlyProjectRequest;
    PostProjectsRequest postProjectsRequest;

    @Test
    public void buscarProjeto(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
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
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBody(name, statusId, statusName, statusLabel, description, enabled, filePath, viewStateId, viewStateName, viewStateLabel);
        Response response = postProjectsRequest.executeRequest();

        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(name);
        String listId = list.get(0);
        String listPojectName = list.get(1);
        String listStatusName = list.get(2);
        String listDescription = list.get(3);
        String listEnabled = list.get(4);
        String listViewStateName = list.get(5);
        getOnlyProjectRequest = new GetOnlyProjectRequest(listId);
        Response responseGet = getOnlyProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.id[0]").toString(), listId, "Validação id");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.name[0]").toString(), listPojectName, "Validação name");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.status.name[0]").toString(), listStatusName, "Validação status name");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.description[0]").toString(), listDescription, "Validação description");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.enabled[0]").toString(), listEnabled, "Validação enabled");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.view_state.name[0]").toString(), listViewStateName, "Validação viewStateName");
        softAssert.assertAll();
    }

    @Test
    public void buscarProjetoInexistente(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        getOnlyProjectRequest = new GetOnlyProjectRequest("9999");
        Response response = getOnlyProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains("Project #9999 not found"), "Validação mensagem apresentada");
        softAssert.assertAll();
    }
}
