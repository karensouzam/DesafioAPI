package com.javarestassuredtemplate.steps;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.requests.GetTokenRequests;
import io.restassured.response.Response;

public class AutenticacaoSteps {
    public static void gerarToken(String usuario, String senha){
        GetTokenRequests getToken = new GetTokenRequests();
        Response response = getToken.executeRequest();
        GlobalParameters.setToken(response.body().jsonPath().get("token").toString());
        System.out.println((response.body().jsonPath().get("token").toString())); // Karen
    }
}
