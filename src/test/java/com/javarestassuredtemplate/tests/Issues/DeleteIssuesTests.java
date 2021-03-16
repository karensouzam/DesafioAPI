package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.DeleteIssuesRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class DeleteIssuesTests extends TestBase{
        DeleteIssuesRequest deleteIssuesRequest;

        @Test
        public void apagarIssue(){
            SoftAssert softAssert = new SoftAssert();

            String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
            ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
            ConsultasDBSteps.insereDescricaoIssue();

            ArrayList<String> projeto = ConsultasDBSteps.retornaProjetos(nomeProjeto);
            ArrayList <String> descricaoIssue = ConsultasDBSteps.retornaDescricaoIssue();
            ConsultasDBSteps.insereIssues(projeto.get(0), descricaoIssue.get(0));
            ArrayList<String> list = ConsultasDBSteps.retornaIssues();

            //Parametros
            int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

            //Busca Projeto
            deleteIssuesRequest = new DeleteIssuesRequest(list.get(6));
            Response response = deleteIssuesRequest.executeRequest();

            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertAll();
            System.out.println(Thread.currentThread().getId());
        }

        @Test
        public void naoDeveApagarIssueInexistente(){
            SoftAssert softAssert = new SoftAssert();

            //Parametros
            int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

            //Busca Projeto
            deleteIssuesRequest = new DeleteIssuesRequest("9999");
            Response response = deleteIssuesRequest.executeRequest();

            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertAll();
            System.out.println(Thread.currentThread().getId());
        }

        @Test
        public void naoDeveApagarIssueInvalida(){
            SoftAssert softAssert = new SoftAssert();

            //Parametros
            String localized = "Issue 0 not found.";
            int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

            //Busca Projeto
            deleteIssuesRequest = new DeleteIssuesRequest("0");
            Response response = deleteIssuesRequest.executeRequest();

            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertTrue(response.body().jsonPath().get("localized").toString().contains(localized), "Validação localized");
            softAssert.assertAll();
            System.out.println(Thread.currentThread().getId());
        }
    }
