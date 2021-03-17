package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.DeleteIssuesNoteRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssueNoteRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class DeleteIssuesNoteTests extends TestBase{
        DeleteIssuesNoteRequest deleteIssuesNoteRequest;
        PostIssuesRequest postIssuesRequest;
        PostIssueNoteRequest postIssueNoteRequest;

        @Test
        public void apagarIssueNote(){
            SoftAssert softAssert = new SoftAssert();

            //Parâmetros
            String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
            String issueText = "test note";
            String issueName = "public";
            int statusCodeEsperado = HttpStatus.SC_OK;

            //Fluxo
            ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
            ConsultasDBSteps.insereDescricaoIssue();
            ArrayList<String> projeto = ConsultasDBSteps.retornaProjetos(nomeProjeto);
            ArrayList<String> list = ConsultasDBSteps.retornaDescricaoIssue();
            String id = projeto.get(0);
            String descricao = list.get(0);
            ConsultasDBSteps.insereIssues(id,descricao);
            ArrayList<String> issues = ConsultasDBSteps.retornaIssues();
            String idIssue = issues.get(6);

            postIssueNoteRequest = new PostIssueNoteRequest(idIssue);
            postIssueNoteRequest.setJsonBody(issueText, issueName);
            Response responseNote = postIssueNoteRequest.executeRequest();
            String idNote = responseNote.body().jsonPath().get("note.id").toString();

            deleteIssuesNoteRequest = new DeleteIssuesNoteRequest(idIssue, idNote);
            Response response2 = deleteIssuesNoteRequest.executeRequest();

            //Asserções
            Assert.assertEquals(response2.statusCode(), statusCodeEsperado);
            softAssert.assertAll();
        }

        @Test
        public void naoDeveapagarIssueNoteInexistente(){
            SoftAssert softAssert = new SoftAssert();

            //Parametros
            int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

            //Fluxo
            deleteIssuesNoteRequest = new DeleteIssuesNoteRequest("9999", "9999");
            Response response = deleteIssuesNoteRequest.executeRequest();

            //Asserções
            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertAll();
        }

        @Test
        public void naodDeveapagarIssueNoteInvalido(){
            SoftAssert softAssert = new SoftAssert();

            //Parametros
            String localized = "Invalid value for 'id'";
            int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

            //Fluxo
            deleteIssuesNoteRequest = new DeleteIssuesNoteRequest("0", "0");
            Response response = deleteIssuesNoteRequest.executeRequest();

            //Asserções
            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertTrue(response.body().jsonPath().get("localized").toString().contains(localized), "Validação localized");
            softAssert.assertAll();
        }
    }
