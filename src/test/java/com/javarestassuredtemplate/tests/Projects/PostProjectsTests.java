package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Projects.PostProjectsRequest;
import com.javarestassuredtemplate.utils.ExcelUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;

public class PostProjectsTests extends TestBase {
    PostProjectsRequest postProjectsRequest;

    @Test
    public void deveIncluirProjetoComSucesso() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String name = "Projeto Teste 2";
        String statusId = "10";
        String statusName = "development";
        String statusLabel = "development";
        String description = "Projeto inserido pelo método Post";
        String enabled = "true";
        String filePath = "/tmp/";
        String viewStateId = "10";
        String viewStateName = "public";
        String viewStateLabel = "public";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBody(name, statusId, statusName, statusLabel, description, enabled, filePath, viewStateId, viewStateName, viewStateLabel);
        Response response = postProjectsRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("project.name").toString(), name, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("project.status.id").toString(), statusId, "Validação statusId");
        softAssert.assertEquals(response.body().jsonPath().get("project.status.name").toString(), statusName, "Validação statusName");
        softAssert.assertEquals(response.body().jsonPath().get("project.status.label").toString(), statusLabel, "Validação statusLabel");
        softAssert.assertEquals(response.body().jsonPath().get("project.description").toString(), description, "Validação description");
        softAssert.assertEquals(response.body().jsonPath().get("project.enabled").toString(), enabled, "Validação enable");
        softAssert.assertEquals(response.body().jsonPath().get("project.view_state.id").toString(), viewStateId, "Validação viewStateId");
        softAssert.assertEquals(response.body().jsonPath().get("project.view_state.name").toString(), viewStateName, "Validação viewStateName");
        softAssert.assertEquals(response.body().jsonPath().get("project.view_state.label").toString(), viewStateLabel, "Validação viewStateLabel");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void deveIncluirProjetoComSucessoComDDT() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        String excelPath = "src/test/java/com/javarestassuredtemplate/resources/DadosProjetos.xlsx";
        String sheetName = "Planilha";

        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

        int tam = excel.getRowCount();

        for (int x = 1; x < tam; x++) {

            String nome = excel.getCellData(x, 0).toString();
            String statusId = excel.getCellData(x, 1).toString();
            String statusName = excel.getCellData(x, 2).toString();
            String statusLabel = excel.getCellData(x, 3).toString();
            String description = excel.getCellData(x, 4).toString();
            String enabled = excel.getCellData(x, 5).toString();
            String filePath = excel.getCellData(x, 6).toString();
            String viewStateId = excel.getCellData(x, 7).toString();
            String viewStateName = excel.getCellData(x, 8).toString();
            String viewStateLabel = excel.getCellData(x, 9).toString();
            int statusCodeEsperado = HttpStatus.SC_CREATED;

            //Fluxo
            postProjectsRequest = new PostProjectsRequest();
            postProjectsRequest.setJsonBody(nome, statusId, statusName, statusLabel, description, enabled, filePath, viewStateId, viewStateName, viewStateLabel);
            Response response = postProjectsRequest.executeRequest();

            //Asserções
            Assert.assertEquals(response.statusCode(), statusCodeEsperado);
            softAssert.assertEquals(response.body().jsonPath().get("project.name").toString(), nome, "Validação name");
            softAssert.assertEquals(response.body().jsonPath().get("project.status.id").toString(), statusId, "Validação statusId");
            softAssert.assertEquals(response.body().jsonPath().get("project.status.name").toString(), statusName, "Validação statusName");
            softAssert.assertEquals(response.body().jsonPath().get("project.status.label").toString(), statusLabel, "Validação statusLabel");
            softAssert.assertEquals(response.body().jsonPath().get("project.description").toString(), description, "Validação description");
            softAssert.assertEquals(response.body().jsonPath().get("project.enabled").toString(), enabled, "Validação enable");
            softAssert.assertEquals(response.body().jsonPath().get("project.view_state.id").toString(), viewStateId, "Validação viewStateId");
            softAssert.assertEquals(response.body().jsonPath().get("project.view_state.name").toString(), viewStateName, "Validação viewStateName");
            softAssert.assertEquals(response.body().jsonPath().get("project.view_state.label").toString(), viewStateLabel, "Validação viewStateLabel");
            softAssert.assertAll();
            System.out.println(Thread.currentThread().getId());
        }
    }

    @Test
    public void naoDeveIncluirProjetoSemDadosObrigatorios() throws IOException {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String name = "";
        String statusId = "10";
        String statusName = "development";
        String statusLabel = "development";
        String description = "Projeto inserido pelo método Post";
        String enabled = "true";
        String filePath = "/tmp/";
        String viewStateId = "10";
        String viewStateName = "public";
        String viewStateLabel = "public";
        String mensagem = "Fatal error";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBody(name, statusId, statusName, statusLabel, description, enabled, filePath, viewStateId, viewStateName, viewStateLabel);
        Response response = postProjectsRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().htmlPath().get().toString().contains(mensagem), "Validação mensagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naodeveIncluirProjetoComNomeDuplicado() {
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        String statusId = "10";
        String statusName = "development";
        String statusLabel = "development";
        String description = "Projeto inserido pelo método Post";
        String enabled = "true";
        String filePath = "/tmp/";
        String viewStateId = "10";
        String viewStateName = "public";
        String viewStateLabel = "public";
        String mensagem = "Fatal error";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        postProjectsRequest = new PostProjectsRequest();
        postProjectsRequest.setJsonBody(nomeProjeto, statusId, statusName, statusLabel, description, enabled, filePath, viewStateId, viewStateName, viewStateLabel);
        Response response = postProjectsRequest.executeRequest();
        Response responseDuplicado = postProjectsRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDuplicado.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(responseDuplicado.body().htmlPath().get().toString().contains(mensagem), "Validação mensagem");
        softAssert.assertAll();
    }

}

