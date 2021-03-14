package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.DeleteIssuesNoteRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssueNoteRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteIssuesNoteTests extends TestBase{
        DeleteIssuesNoteRequest deleteIssuesNoteRequest;
        PostIssuesRequest postIssuesRequest;
        PostIssueNoteRequest postIssueNoteRequest;

        @Test
        public void apagarIssueNote(){
            SoftAssert softAssert = new SoftAssert();

            //Parâmetros
            String summary ="SUMARIO";
            String description="DESCRICAO";
            String categoryName="General";
            String projectName="PROJETO TESTE 1";
            String issueText = "test note";
            String issueName = "public";
            int statusCodeEsperado = HttpStatus.SC_OK;

            //Fluxo
            postIssuesRequest = new PostIssuesRequest();
            postIssuesRequest.setJsonBody(summary, description, categoryName, projectName);
            Response response = postIssuesRequest.executeRequest();

            String id = response.body().jsonPath().get("issue.id").toString();

            postIssueNoteRequest = new PostIssueNoteRequest(id);
            postIssueNoteRequest.setJsonBody(issueText, issueName);
            Response response1 = postIssueNoteRequest.executeRequest();

            String idNote = response1.body().jsonPath().get("note.id").toString();

            //Fluxo
            deleteIssuesNoteRequest = new DeleteIssuesNoteRequest(id, idNote);
            Response response2 = deleteIssuesNoteRequest.executeRequest();

            //Asserções
            Assert.assertEquals(response2.statusCode(), statusCodeEsperado);
            softAssert.assertAll();
            System.out.println(Thread.currentThread().getId());

        }
        @Test
        public void apagarIssueNoteInexistente(){
            SoftAssert softAssert = new SoftAssert();

            //Parametros
            int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

            //Fluxo
            deleteIssuesNoteRequest = new DeleteIssuesNoteRequest("9999", "9999");
            Response response = deleteIssuesNoteRequest.executeRequest();

            //Asserções
            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertAll();
            System.out.println(Thread.currentThread().getId());
        }
    }
