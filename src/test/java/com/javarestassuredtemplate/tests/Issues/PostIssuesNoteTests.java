package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.PostIssueNoteRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostIssuesNoteTests extends TestBase {
    PostIssuesRequest postProblemasRequest;
    PostIssueNoteRequest postIssueNoteRequest;

    @Test
    public void deveIncluirNoteComSucesso() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String projectName="PROJETO TESTE 1";
        String issueText = "test note";
        String issueName = "public";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postProblemasRequest = new PostIssuesRequest();
        postProblemasRequest.setJsonBody(summary, description, categoryName, projectName);
        Response response = postProblemasRequest.executeRequest();

        String id = response.body().jsonPath().get("issue.id").toString();

        postIssueNoteRequest = new PostIssueNoteRequest(id);
        postIssueNoteRequest.setJsonBody(issueText, issueName);
        Response responseNote = postIssueNoteRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseNote.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(responseNote.body().jsonPath().get("note.text").toString(), issueText, "Validação text");
        softAssert.assertEquals(responseNote.body().jsonPath().get("note.view_state.name").toString(), issueName, "Validação name");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naoDeveIncluirNoteSemDadosObrigatorios() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String projectName="PROJETO TESTE 1";
        String issueText = "";
        String issueName = "public";
        String mensagem = "Issue note not specified.";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postProblemasRequest = new PostIssuesRequest();
        postProblemasRequest.setJsonBody(summary, description, categoryName, projectName);
        Response response = postProblemasRequest.executeRequest();

        String id = response.body().jsonPath().get("issue.id").toString();

        postIssueNoteRequest = new PostIssueNoteRequest(id);
        postIssueNoteRequest.setJsonBody(issueText, issueName);
        Response responseNote = postIssueNoteRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseNote.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(responseNote.body().jsonPath().get("message").toString(), mensagem, "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naoDeveIncluirNoteEmProjetoInvalido() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String issueText = "texto";
        String issueName = "public";
        String localized = "Issue 9999 not found.";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postIssueNoteRequest = new PostIssueNoteRequest("9999");
        postIssueNoteRequest.setJsonBody(issueText, issueName);
        Response responseNote = postIssueNoteRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseNote.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(responseNote.body().jsonPath().get("localized").toString(), localized, "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
