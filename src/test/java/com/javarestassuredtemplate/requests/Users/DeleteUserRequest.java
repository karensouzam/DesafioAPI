package com.javarestassuredtemplate.requests.Users;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class DeleteUserRequest extends RequestRestBase{

    public DeleteUserRequest(String id){
        requestService = "/api/rest/users/" +id;
        method = Method.DELETE;
    }
}



