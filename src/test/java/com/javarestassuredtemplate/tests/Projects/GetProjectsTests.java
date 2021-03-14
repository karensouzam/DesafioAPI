package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.GetProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetProjectsTests extends TestBase {
    GetProjectsRequest getProjetosRequest;

    @Test
    public void buscarProjetos(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String nome = "PROJETO TESTE 1";
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nome);
        String projectName = list.get(1);
        String statusName = list.get(2);
        String description = list.get(3);
        String projectEnabled = list.get(4);
        String viewStateName = list.get(5);
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getProjetosRequest = new GetProjectsRequest();
        Response response = getProjetosRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("projects.name[0]").toString(), projectName, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("projects.status.name[0]").toString(), statusName, "Validação statusId");
        softAssert.assertEquals(response.body().jsonPath().get("projects.description[0]").toString(), description, "Validação statusName");
        softAssert.assertEquals(response.body().jsonPath().get("projects.enabled[0]").toString(), projectEnabled, "Validação enable");
        softAssert.assertEquals(response.body().jsonPath().get("projects.view_state.name[0]").toString(), viewStateName, "Validação viewStateName");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

}
