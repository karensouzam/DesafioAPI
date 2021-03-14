package com.javarestassuredtemplate.tests.Projects;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Projects.UpdateProjectRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class UpdateProjectsTests extends TestBase {
    UpdateProjectRequest updateProjectRequest;

    @Test
    public void deveAtualizarProjetoComSucesso() {
       SoftAssert softAssert = new SoftAssert();

       //Parâmetros

       String nome = "PROJETO TESTE 1";
       String nomeAlterado = "PROJETO TESTE 1 ALTERADO";

       int statusCodeEsperado = HttpStatus.SC_OK;

       //Fluxo
        ArrayList<String> list = ConsultasDBSteps.retornaProjetos(nome);
        updateProjectRequest = new UpdateProjectRequest(list.get(0));

        String id = list.get(0);
        String enabled = list.get(4);

        updateProjectRequest.setJsonBody(id, nomeAlterado, enabled);
        Response response = updateProjectRequest.executeRequest();

        ArrayList<String> listAlterado = ConsultasDBSteps.retornaProjetoAlterado(nomeAlterado);

        //Asserções
        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertEquals(listAlterado.get(0), nomeAlterado, "Validação name");
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}