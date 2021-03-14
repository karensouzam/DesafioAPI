package com.javarestassuredtemplate.tests;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.GetTokenRequests;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class AutorizacaoTests extends TestBase {
    GetTokenRequests getToken;

    @Test
    public void deveFazerLoginComSucesso(){
        SoftAssert softAssert = new SoftAssert();

        //Parâmetros
        int statusCodeEsperado = HttpStatus.SC_OK;
        String email = "karensmarques@hotmail.com";
        String senha = "108465";

        //Fluxo
        getToken = new GetTokenRequests();
        getToken.setJsonBody(email, senha);
        Response response = getToken.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);

    }
}
