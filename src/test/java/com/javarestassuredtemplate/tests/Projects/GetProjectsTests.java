package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.GetProjectsRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.annotation.NotThreadSafe;
import org.junit.runner.notification.RunListener.ThreadSafe;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetProjectsTests extends TestBase {
    GetProjectsRequest getProjetosRequest;

    @Test
    public void buscarProjetos(){
        SoftAssert softAssert = new SoftAssert();

        String nome = "PROJETO TESTE 1";
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nome);

        getProjetosRequest = new GetProjectsRequest();
        Response response = getProjetosRequest.executeRequest();

        String projectName = "[" + list.get(1) + "]";
        String statusName = "[" + list.get(2) + "]";
        String description = "[" + list.get(3) + "]";
        String projectEnabled = "[" + list.get(4) + "]";
        String viewStateName = "[" + list.get(5) + "]";
        int statusCodeEsperado = HttpStatus.SC_OK;

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(response.body().jsonPath().get("projects.name").toString(), projectName, "Validação name");
        softAssert.assertEquals(response.body().jsonPath().get("projects.status.name").toString(), statusName, "Validação statusId");
        softAssert.assertEquals(response.body().jsonPath().get("projects.description").toString(), description, "Validação statusName");
        softAssert.assertEquals(response.body().jsonPath().get("projects.enabled").toString(), projectEnabled, "Validação enable");
        softAssert.assertEquals(response.body().jsonPath().get("projects.view_state.name").toString(), viewStateName, "Validação viewStateName");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }

}
