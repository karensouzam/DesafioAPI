package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.GetProjectsRequest;
import com.javarestassuredtemplate.requests.Projects.PostProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetProjectsTests extends TestBase {
    GetProjectsRequest getProjetosRequest;
    PostProjectsRequest postProjectsRequest;

    @Test
    public void buscarProjetos(){
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

        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBody(name, statusId, statusName, statusLabel, description, enabled, filePath, viewStateId, viewStateName, viewStateLabel);
        Response response = postProjectsRequest.executeRequest();
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(name);
        String listProjectName = list.get(1);
        String listStatusName = list.get(2);
        String listDescription = list.get(3);
        String listProjectEnabled = list.get(4);
        String listViewStateName = list.get(5);
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getProjetosRequest = new GetProjectsRequest();
        Response responseGet = getProjetosRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseGet.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.name[0]").toString(), listProjectName, "Validação name");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.status.name[0]").toString(), listStatusName, "Validação statusId");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.description[0]").toString(), listDescription, "Validação statusName");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.enabled[0]").toString(), listProjectEnabled, "Validação enable");
        softAssert.assertEquals(responseGet.body().jsonPath().get("projects.view_state.name[0]").toString(), listViewStateName, "Validação viewStateName");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

}
