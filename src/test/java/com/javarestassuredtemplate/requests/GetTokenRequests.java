package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetTokenRequests extends RequestRestBase {
    public GetTokenRequests(){
        url= GlobalParameters.URL_TOKEN;

        //requestService = "token/"+usuario+"/"+senha;
        requestService = "/signin";
        method = Method.POST;

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();//KAREN
        System.out.println(requestService);
    }

    public void setJsonBody(String email,
                            String senha){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/AutorizacaoJson.json");

        jsonBody = jsonBody.replace("$email", email)
                .replace("$senha", senha);
    }


}
