package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Issues.GetIssueFileRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetIssueFileTests extends TestBase {
    GetIssueFileRequest getIssueFileRequest;

    @Test
    public void buscarArquivoInvalido(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        String messagem = "Issue #9999 not found";
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;

        //Fluxo
        getIssueFileRequest = new GetIssueFileRequest("9999");
        Response response = getIssueFileRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("message").toString(), messagem, "Validação messagem");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}