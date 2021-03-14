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
    public void buscarProblemaEspecifico(){
        SoftAssert softAssert = new SoftAssert();

        ArrayList<String> projeto = ConsultasDBSteps.retornaProjetos("PROJETO TESTE 1");
        ArrayList <String> descricaoProblema = ConsultasDBSteps.retornaDescricaoIssue();
        ConsultasDBSteps.insereIssues(projeto.get(0), descricaoProblema.get(0));

        ArrayList<String> list = ConsultasDBSteps.retornaIssues();

        String id = "[" + list.get(6) + "]";
        String summary = "[" + list.get(0) + "]";
        String description = "[" + list.get(1) + "]";
        String stepsToReproduce = "[" + list.get(2) + "]";
        String additionalInformation = "[" + list.get(3) + "]";
        String projectName = "[" + list.get(4) + "]";
        String category = "[" + list.get(5) + "]";
        int statusCodeEsperado = HttpStatus.SC_OK;

        getAnIssueRequest = new GetAnIssuesRequest(list.get(6));
        Response response = getAnIssueRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issues.id").toString(), id, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("issues.summary").toString(), summary, "Validação summary");
        softAssert.assertEquals(response.body().jsonPath().get("issues.description").toString(), description, "Validação descrição");
        softAssert.assertEquals(response.body().jsonPath().get("issues.steps_to_reproduce").toString(), stepsToReproduce, "Validação passos para reprodução");
        softAssert.assertEquals(response.body().jsonPath().get("issues.additional_information").toString(), additionalInformation, "Validação informação adicional");
        softAssert.assertEquals(response.body().jsonPath().get("issues.project.name").toString(), projectName, "Validação nome projeto");
        softAssert.assertEquals(response.body().jsonPath().get("issues.category.name").toString(), category, "Validação nome categoria");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void buscarProblemaInexistente(){
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