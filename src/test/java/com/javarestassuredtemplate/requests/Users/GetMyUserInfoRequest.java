package com.javarestassuredtemplate.requests.Users;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetMyUserInfoRequest extends RequestRestBase{

    public GetMyUserInfoRequest(){
        requestService = "/api/rest/users/me";
        method = Method.GET;
    }

}



