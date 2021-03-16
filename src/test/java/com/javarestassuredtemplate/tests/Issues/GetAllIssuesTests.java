package com.javarestassuredtemplate.tests.Issues;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.requests.Issues.DeleteIssuesRequest;
import com.javarestassuredtemplate.requests.Issues.GetAllIssuesRequest;
import com.javarestassuredtemplate.requests.Issues.GetAnIssuesRequest;
import com.javarestassuredtemplate.requests.Issues.PostIssuesRequest;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;

public class GetAllIssuesTests extends TestBase {
    GetAllIssuesRequest getAllIssuesRequest;
    PostIssuesRequest postIssuesRequest;

    @Test
    public void buscarIssue(){
        SoftAssert softAssert = new SoftAssert();

        String nomeProjeto = "PROJETO TESTE " + GeneralUtils.getNumeroAleatorio();
        ConsultasDBSteps.insereDadosProjeto(nomeProjeto);
        ConsultasDBSteps.insereDescricaoIssue();
        ConsultasDBSteps.apagaIssues();

        ArrayList<String> projeto = ConsultasDBSteps.retornaProjetos(nomeProjeto);
        ArrayList <String> descricaoIssue = ConsultasDBSteps.retornaDescricaoIssue();
        ConsultasDBSteps.insereIssues(projeto.get(0), descricaoIssue.get(0));
        ArrayList<String> list = ConsultasDBSteps.retornaIssues();

        //Parametros
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Busca Projeto
        getAllIssuesRequest = new GetAllIssuesRequest();
        Response response = getAllIssuesRequest.executeRequest();

        Assert.assertEquals(response.statusCode(), statusCodeEsperado);
        softAssert.assertAll();
        System.out.println(Thread.currentThread().getId());
    }
}
