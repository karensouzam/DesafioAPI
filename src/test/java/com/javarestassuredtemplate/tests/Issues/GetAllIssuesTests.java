package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.GetAllIssuesRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetAllIssuesTests extends TestBase {
    GetAllIssuesRequest getAllIssuesRequest;

    @Test
    public void buscarIssue(){
        SoftAssert softAssert = new SoftAssert();

        ConsultasDBSteps.insereDadosProjeto();
        ConsultasDBSteps.insereDescricaoIssue();

        ArrayList<String> projeto = ConsultasDBSteps.retornaProjetos("PROJETO TESTE 1");
        ArrayList <String> descricaoProblema = ConsultasDBSteps.retornaDescricaoIssue();
        ConsultasDBSteps.insereIssues(projeto.get(0), descricaoProblema.get(0));
        ArrayList<String> list = ConsultasDBSteps.retornaIssues();

        //Parâmetros
        String summary = list.get(0);
        String description = list.get(1);
        String stepsToReproduce = list.get(2);
        String additionalInformation = list.get(3);
        String projectName = list.get(4);
        String category = list.get(5);
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getAllIssuesRequest = new GetAllIssuesRequest();
        Response response = getAllIssuesRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issues.summary[0]").toString(), summary, "Validação summary");
        softAssert.assertEquals(response.body().jsonPath().get("issues.description[0]").toString(), description, "Validação descrição");
        softAssert.assertEquals(response.body().jsonPath().get("issues.steps_to_reproduce[0]").toString(),stepsToReproduce, "Validação passos para reprodução");
        softAssert.assertEquals(response.body().jsonPath().get("issues.additional_information[0]").toString(), additionalInformation, "Validação informação adicional");
        softAssert.assertEquals(response.body().jsonPath().get("issues.project.name[0]").toString(), projectName, "Validação nome projeto");
        softAssert.assertEquals(response.body().jsonPath().get("issues.category.name[0]").toString(), category, "Validação nome categoria");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
