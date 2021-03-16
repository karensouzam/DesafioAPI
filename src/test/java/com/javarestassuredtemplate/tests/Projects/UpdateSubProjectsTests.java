package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.UpdateSubProjectRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class UpdateSubProjectsTests extends TestBase {
    UpdateSubProjectRequest updateSubProjectRequest;

    @Test
    public void naodeveAtualizarSubProjectComValorInvalido() {
       SoftAssert softAssert = new SoftAssert();

       //Parâmetros
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        String inherit_parent = "true";
        String localized = "Invalid value for 'subproject_id'";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

       //Fluxo
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nomeProjeto);
        String id = list.get(0);
        updateSubProjectRequest = new UpdateSubProjectRequest(id, "0");
        updateSubProjectRequest.setJsonBody(nomeProjeto, inherit_parent);
        Response response = updateSubProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("localized").toString().contains(localized), "Validação localized");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naodeveAtualizarSubProjectEmProjetoInexistente() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String nomeProjeto = "PROJETO TESTE 1";
        String inherit_parent = "true";
        String mensagem = "Project '9999' not found";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        updateSubProjectRequest = new UpdateSubProjectRequest("9999", "9999");
        updateSubProjectRequest.setJsonBody(nomeProjeto, inherit_parent);
        Response response = updateSubProjectRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains(mensagem), "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
