package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.PostProjectVersionRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class PostProjectVersionTests extends TestBase {
    PostProjectVersionRequest projectVersionRequest;

    @Test
    public void deveIncluirVersaoProjetoComSucesso() {
       SoftAssert softAssert = new SoftAssert();

       //Parâmetros
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
       String name = "v2.0.0";
       String description = "Major new version";
       String released = "true";
       String obsolete = "false";
       String timestamp = "2020-02-20";
       int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

       //Fluxo
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nomeProjeto);
        projectVersionRequest = new PostProjectVersionRequest(list.get(0));
        projectVersionRequest.setJsonBody(name, description, released, obsolete, timestamp);
        Response response = projectVersionRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
    }

    @Test
    public void naoDeveIncluirVersaoProjetoSemDadosObrigatorios() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        String name = "";
        String description = "Major new version";
        String released = "true";
        String obsolete = "false";
        String timestamp = "2020-02-20";
        String mensagem = "Invalid version name";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nomeProjeto);
        projectVersionRequest = new PostProjectVersionRequest(list.get(0));
        projectVersionRequest.setJsonBody(name, description, released, obsolete, timestamp);
        Response response = projectVersionRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains(mensagem), "Validação mensagem");
        softAssert.assertAll();
    }

    @Test
    public void naoDeveIncluirVersaoDuplicada() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        String name = "v2.0.0";
        String description = "Major new version";
        String released = "true";
        String obsolete = "false";
        String timestamp = "2020-02-20";
        String mensagem = "";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nomeProjeto);
        projectVersionRequest = new PostProjectVersionRequest(list.get(0));
        projectVersionRequest.setJsonBody(name, description, released, obsolete, timestamp);
        Response response = projectVersionRequest.executeRequest();
        Response responseDuplicado = projectVersionRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDuplicado.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
    }
}
