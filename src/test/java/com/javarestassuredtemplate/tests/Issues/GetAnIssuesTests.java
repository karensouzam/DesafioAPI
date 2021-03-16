package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.GetAnIssuesRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetAnIssuesTests extends TestBase {
    GetAnIssuesRequest getAnIssueRequest;

    @Test
    public void buscarIssueEspecifico(){
        SoftAssert softAssert = new SoftAssert();

        ConsultasDBSteps.insereDadosProjeto();
        ConsultasDBSteps.insereDescricaoIssue();

        ArrayList<String> projeto = ConsultasDBSteps.retornaProjetos("PROJETO TESTE 1");
        ArrayList <String> descricaoProblema = ConsultasDBSteps.retornaDescricaoIssue();
        ConsultasDBSteps.insereIssues(projeto.get(0), descricaoProblema.get(0));
        ArrayList<String> list = ConsultasDBSteps.retornaIssues();

        String id = list.get(6);
        String summary = list.get(0);
        String description = list.get(1);
        String stepsToReproduce = list.get(2);
        String additionalInformation = list.get(3);
        String projectName = list.get(4);
        String category = list.get(5);
        int statusCodeEsperado = HttpStatus.SC_OK;

        getAnIssueRequest = new GetAnIssuesRequest(list.get(6));
        Response response = getAnIssueRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issues.id[0]").toString(), id, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("issues.summary[0]").toString(), summary, "Validação summary");
        softAssert.assertEquals(response.body().jsonPath().get("issues.description[0]").toString(), description, "Validação descrição");
        softAssert.assertEquals(response.body().jsonPath().get("issues.steps_to_reproduce[0]").toString(), stepsToReproduce, "Validação passos para reprodução");
        softAssert.assertEquals(response.body().jsonPath().get("issues.additional_information[0]").toString(), additionalInformation, "Validação informação adicional");
        softAssert.assertEquals(response.body().jsonPath().get("issues.project.name[0]").toString(), projectName, "Validação nome projeto");
        softAssert.assertEquals(response.body().jsonPath().get("issues.category.name[0]").toString(), category, "Validação nome categoria");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naoDevebuscarIssueInexistente(){
        SoftAssert softAssert = new SoftAssert();

        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        getAnIssueRequest = new GetAnIssuesRequest("9999");
        Response response = getAnIssueRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains("Issue #9999 not found"), "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
