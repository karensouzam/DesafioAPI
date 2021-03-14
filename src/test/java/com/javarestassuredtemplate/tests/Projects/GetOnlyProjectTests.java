package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.GetOnlyProjectRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetOnlyProjectTests extends TestBase {
    GetOnlyProjectRequest getOnlyProjectRequest;

    @Test
    public void buscarProjeto(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String nome = "PROJETO TESTE 1";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Busca Projeto
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nome);
        getOnlyProjectRequest = new GetOnlyProjectRequest(list.get(0));

        String id = list.get(0);
        String projectName = list.get(1);
        String statusName = list.get(2);
        String description = list.get(3);
        String enabled = list.get(4);
        String viewStateName = list.get(5);

        Response response = getOnlyProjectRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("projects.id[0]").toString(), id, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("projects.name[0]").toString(), projectName, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("projects.status.name[0]").toString(), statusName, "Validação status name");
        softAssert.assertEquals(response.body().jsonPath().get("projects.description[0]").toString(), description, "Validação description");
        softAssert.assertEquals(response.body().jsonPath().get("projects.enabled[0]").toString(), enabled, "Validação enabled");
        softAssert.assertEquals(response.body().jsonPath().get("projects.view_state.name[0]").toString(), viewStateName, "Validação viewStateName");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void buscarProjetoInexistente(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        getOnlyProjectRequest = new GetOnlyProjectRequest("9999");
        Response response = getOnlyProjectRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains("Project #9999 not found"), "Validação mensagem apresentada");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

}
