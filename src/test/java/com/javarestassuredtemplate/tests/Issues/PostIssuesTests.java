package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostIssuesTests extends TestBase {
    PostIssuesRequest postIssuesRequest;

    @Test
    public void deveIncluirIssueComSucesso() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        int statusCodeEsperado = HttpStatus.SC_CREATED;
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, nomeProjeto);
        Response response = postIssuesRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issue.summary").toString(), summary, "Validação issue.summary");
        softAssert.assertEquals(response.body().jsonPath().get("issue.description").toString(), description, "Validação description");
        softAssert.assertEquals(response.body().jsonPath().get("issue.project.name").toString(), nomeProjeto, "Validação projectName");
        softAssert.assertEquals(response.body().jsonPath().get("issue.category.name").toString(), categoryName, "Validação categoryName");
        softAssert.assertAll();
    }

    @Test
    public void naoDeveIncluirIssueSemInformacaoObrigatoria() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="";
        String description="DESCRICAO";
        String categoryName="General";
        String projectName="PROJETO TESTE 1";
        String mensagem= "Summary not specified";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, projectName);
        Response response = postIssuesRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), mensagem, "Validação mensagem");
        softAssert.assertAll();
    }

    @Test
    public void naoDeveIncluirIssueEmProjetoInexistente() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String projectName="PROJETO";
        String mensagem= "Project not specified";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, projectName);
        Response response = postIssuesRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), mensagem, "Validação mensagem");
        softAssert.assertAll();
    }
}
