package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.GetAnIssuesRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetAnIssuesTests extends TestBase {
    GetAnIssuesRequest getAnIssueRequest;
    PostIssuesRequest postIssuesRequest;

    @Test
    public void buscarIssueEspecifico(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
        ConsultasDBSteps.insereDescricaoIssue();

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, nomeProjeto);
        Response responsePost = postIssuesRequest.executeRequest();

        ArrayList<String> projeto = ConsultasDBSteps.retornaProjetos(nomeProjeto);
        ArrayList <String> descricaoProblema = ConsultasDBSteps.retornaDescricaoIssue();
        ArrayList<String> list = ConsultasDBSteps.retornaIssues();
        String id = list.get(6);
        String listSummary = list.get(0);
        String listDescription = list.get(1);
        String projectName = list.get(4);
        String category = list.get(5);
        int statusCodeEsperado = HttpStatus.SC_OK;

        getAnIssueRequest = new GetAnIssuesRequest(id);
        Response response = getAnIssueRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issues.id[0]").toString(), id, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("issues.summary[0]").toString(), listSummary, "Validação summary");
        softAssert.assertEquals(response.body().jsonPath().get("issues.description[0]").toString(), listDescription, "Validação descrição");
        softAssert.assertEquals(response.body().jsonPath().get("issues.project.name[0]").toString(), projectName, "Validação nome projeto");
        softAssert.assertEquals(response.body().jsonPath().get("issues.category.name[0]").toString(), category, "Validação nome categoria");
        softAssert.assertAll();
    }

    @Test
    public void naoDevebuscarIssueInexistente(){
        SoftAssert softAssert = new SoftAssert();
        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        //Fluxo
        getAnIssueRequest = new GetAnIssuesRequest("9999");
        Response response = getAnIssueRequest.executeRequest();
        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains("Issue #9999 not found"), "Validação mensagem");
        softAssert.assertAll();
    }
}
