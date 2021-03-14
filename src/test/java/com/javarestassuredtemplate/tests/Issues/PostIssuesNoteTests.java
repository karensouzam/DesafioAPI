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
        Response response1 = postIssueNoteRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response1.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response1.body().jsonPath().get("note.text").toString(), issueText, "Validação text");
        softAssert.assertEquals(response1.body().jsonPath().get("note.view_state.name").toString(), issueName, "Validação name");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}