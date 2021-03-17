package com.javarestassuredtemplate.tests.Users;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Users.DeleteUserRequest;
import com.javarestassuredtemplate.requests.Users.PostUserRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class DeleteUsuarioTest extends TestBase  {
    PostUserRequest postUserRequest;
    DeleteUserRequest deleteUserRequest;

    @Test
    public void deveApagarUsuarioComSucesso(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        ConsultasDBSteps.insereUsuarioDesprotegido();
        ArrayList<String> list = ConsultasDBSteps.retornaUsuarioEspecifico();
        String id = list.get(0);
        deleteUserRequest = new DeleteUserRequest(id);
        Response responseDelete = deleteUserRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDelete.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
    }

    @Test
    public void naoDeveApagarUsuarioProtegido(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String name = "Usuario" + GeneralUtils.getNumeroAleatorio();
        String password = "123456";
        String realName = "Usuario inserido pela automação";
        String email = "usario@email.com.br";
        String accessLevelName = "updater";
        String enabled = "true";
        String protectedJson = "false";
        String localized = "This account is protected. You are not allowed to access this until the account protection is lifted";
        int statusCodeEsperado = HttpStatus.SC_FORBIDDEN;

        //Fluxo
        postUserRequest = new PostUserRequest();
        postUserRequest.setJsonBody(name, password, realName, email, accessLevelName, enabled, protectedJson);
        Response response = postUserRequest.executeRequest();
        ArrayList<String> list = ConsultasDBSteps.retornaUsuarioEspecifico();
        String id = list.get(0);
        deleteUserRequest = new DeleteUserRequest(id);
        Response responseDelete = deleteUserRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDelete.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(responseDelete.body().jsonPath().get("localized").toString().contains(localized), "Validação mensagem");
        softAssert.assertAll();
    }

    @Test
    public void naoDeveApagarUsuarioInexistente(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_NO_CONTENT;

        //Fluxo
        deleteUserRequest = new DeleteUserRequest("9999");
        Response responseDelete = deleteUserRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDelete.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
    }

    @Test
    public void naoDeveApagarUsuarioInvalido(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String mensagem = "Invalid user id";
        int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

        //Fluxo
        deleteUserRequest = new DeleteUserRequest("0");
        Response responseDelete = deleteUserRequest.executeRequest();

        //Asserções
        Assert.assertEquals(responseDelete.statusCode(), statusCodeEsperado);
        softAssert.assertTrue(responseDelete.body().jsonPath().get("message").toString().contains(mensagem), "Validação mensagem");
        softAssert.assertAll();
    }
}
