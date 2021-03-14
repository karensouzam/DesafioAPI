package com.javarestassuredtemplate.tests.Users;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Users.GetMyUserInfoRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.annotation.NotThreadSafe;
import org.junit.runner.notification.RunListener;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetMyUserInfoTest extends TestBase {
    GetMyUserInfoRequest getMyUserInfoRequest;

    @Test
    public void buscarMeuUsuario(){
        SoftAssert softAssert = new SoftAssert();

        ArrayList<String> list = ConsultasDBSteps.retornaUsuarios();

        getMyUserInfoRequest = new GetMyUserInfoRequest();
        Response response = getMyUserInfoRequest.executeRequest();

        String id = list.get(0);
        String name = list.get(1);
        String email = list.get(2);
        String accessLevel = list.get(3);
        int statusCodeEsperado = HttpStatus.SC_OK;

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("id").toString(), id, "Validação id");
        softAssert.assertEquals(response.body().jsonPath().get("name").toString(), name, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("email").toString(), email, "Validação email");
        softAssert.assertEquals(response.body().jsonPath().get("access_level.id").toString(), accessLevel, "Validação accessLevel");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
