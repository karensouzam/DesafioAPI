package com.javarestassuredtemplate.tests.Users;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Users.DeleteUserRequest;
import com.javarestassuredtemplate.requests.Users.PostUserRequest;
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
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void naoDeveApagarUsuarioProtegido(){
        SoftAssert softAssert = new SoftAssert();

        //Parametros
        String name = "Usuario2";
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
        System.out.println(Thread.currentThread().getId());
    }

}
