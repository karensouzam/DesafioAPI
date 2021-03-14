package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.PostSubProjectRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class PostSubProjectsTests extends TestBase {
    PostSubProjectRequest postSubProjectRequest;

    @Test
    public void naodeveIncluirSubProjectComNomeDoProjeto() {
       SoftAssert softAssert = new SoftAssert();

       //Parâmetros
        String nomeProjeto = "PROJETO TESTE 1";
        String inherit_parent = "true";
        String mensagem = "Project can't be subproject of itself";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

       //Fluxo
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nomeProjeto);
        String id = list.get(0);
        postSubProjectRequest = new PostSubProjectRequest(id);
        postSubProjectRequest.setJsonBody(nomeProjeto, inherit_parent);
        Response response = postSubProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains(mensagem), "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naodeveIncluirSubProjectEmProjetoInexistente() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String nomeProjeto = "PROJETO TESTE 1";
        String inherit_parent = "true";
        String mensagem = "Project '9999' not found";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        postSubProjectRequest = new PostSubProjectRequest("9999");
        postSubProjectRequest.setJsonBody(nomeProjeto, inherit_parent);
        Response response = postSubProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains(mensagem), "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naodeveIncluirSubProjectEmProjetoInvalido() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String nomeProjeto = "PROJETO TESTE 1";
        String inherit_parent = "true";
        String localized = "Invalid value for 'project_id'";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        postSubProjectRequest = new PostSubProjectRequest("0");
        postSubProjectRequest.setJsonBody(nomeProjeto, inherit_parent);
        Response response = postSubProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("localized").toString().contains(localized), "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
