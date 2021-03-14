package com.javarestassuredtemplate.tests.Users;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.Users.PostUserRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.annotation.NotThreadSafe;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostUsuarioTest extends TestBase  {
    PostUserRequest postUserRequest;

    @Test
    public void CriarUsuarioComSucesso(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String name = "Usuario2";
        String password = "123456";
        String realName = "Usuario inserido pela automação";
        String email = "usario@email.com.br";
        String accessLevelName = "updater";
        String enabled = "true";
        String protectedJson = "false";
        int statusCodeEsperado = HttpStatus.SC_CREATED;

        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBody(name, password, realName, email, accessLevelName, enabled, protectedJson);
        Response response = postUserRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("user.name").toString(), name, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("user.real_name").toString(), realName, "Validação realName");
        softAssert.assertEquals(response.body().jsonPath().get("user.email").toString(), email, "Validação email");
        softAssert.assertTrue(response.body().jsonPath().get("user.email").toString().matches("^(.+)@(.+)$"), "Validação email com regex");
        softAssert.assertEquals(response.body().jsonPath().get("user.access_level.name").toString(), accessLevelName, "Validação accessLevel name");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void CriarUsuarioDuplicado(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String name = "administrator";
        String password = "123456";
        String realName = "Usuario inserido pela automação";
        String email = "usario@email.com.br";
        String accessLevelName = "updater";
        String enabled = "true";
        String protectedJson = "false";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBody(name, password, realName, email, accessLevelName, enabled, protectedJson);
        Response response = postUserRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("localized").toString(), "That username is already being used. Please go back and select another one.", "Validação mensagem erro");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

    @Test

    public void CriarUsuarioSemDadosObrigatorios(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String name = " ";
        String password = "123456";
        String realName = "Usuario inserido pela automação";
        String email = " ";
        String accessLevelName = "updater";
        String enabled = "true";
        String protectedJson = "false";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBody(name, password, realName, email, accessLevelName, enabled, protectedJson);
        Response response = postUserRequest.executeRequest();

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(response.body().jsonPath().get("message").toString().contains("Invalid username"),  "Validação message");
        softAssert.assertTrue(response.body().jsonPath().get("localized").toString().contains("The username is invalid. Usernames may only contain Latin letters, numbers, spaces, hyphens, dots, plus signs and underscores."), "Validação Localized");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}