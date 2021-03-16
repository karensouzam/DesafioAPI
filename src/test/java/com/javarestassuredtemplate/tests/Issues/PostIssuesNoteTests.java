package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.PostIssueNoteRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostIssuesNoteTests extends TestBase {
    PostIssuesRequest postIssuesRequest;
    PostIssueNoteRequest postIssueNoteRequest;

    @Test
    public void deveIncluirNoteComSucesso() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String summary ="SUMARIO";
        String description="DESCRICAO";
        String categoryName="General";
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        String issueText = "test note";
        String issueName = "public";
        int statusCodeEsperado = HttpStatus.SC_CREATED;
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
        ConsultasDBSteps.insereDescricaoIssue();

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, nomeProjeto);
        Response response = postIssuesRequest.executeRequest();

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
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        String issueText = "";
        String issueName = "public";
        String mensagem = "Issue note not specified.";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);

        //Fluxo
        postIssuesRequest = new PostIssuesRequest();
        postIssuesRequest.setJsonBody(summary, description, categoryName, nomeProjeto);
        Response response = postIssuesRequest.executeRequest();

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
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

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
