package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostIssuesTests extends TestBase {
    PostIssuesRequest postIssuesRequest;

    /*@Test
    public void deveIncluirIssueComAnexo() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String projectName="PROJETO TESTE 1";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, projectName);
        Response response = postIssuesRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issue.summary").toString(), summary, "Validação issue.summary");
        softAssert.assertEquals(response.body().jsonPath().get("issue.description").toString(), description, "Validação description");
        softAssert.assertEquals(response.body().jsonPath().get("issue.project.name").toString(), projectName, "Validação projectName");
        softAssert.assertEquals(response.body().jsonPath().get("issue.category.name").toString(), categoryName, "Validação categoryName");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }*/

    @Test
    public void deveIncluirIssueComSucesso() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String projectName="PROJETO TESTE 1";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, projectName);
        Response response = postIssuesRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("issue.summary").toString(), summary, "Validação issue.summary");
        softAssert.assertEquals(response.body().jsonPath().get("issue.description").toString(), description, "Validação description");
        softAssert.assertEquals(response.body().jsonPath().get("issue.project.name").toString(), projectName, "Validação projectName");
        softAssert.assertEquals(response.body().jsonPath().get("issue.category.name").toString(), categoryName, "Validação categoryName");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
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
        System.out.println(Thread.currentThread().getId());
    }
}
