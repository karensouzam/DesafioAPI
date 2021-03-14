package com.javarestassuredtemplate.requests.Users;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostUserRequest extends RequestRestBase{

    public PostUserRequest(){
        requestService = "/api/rest/users";
        method = Method.POST;
    }

    public void setJsonBody(String name,
                            String password,
                            String realName,
                            String email,
                            String accessLevelName,
                            String enabled,
                            String protectedJson){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostUserJson.json");

        jsonBody = jsonBody.replace("$name", name)
                .replace("$password", password)
                .replace("$realName", realName)
                .replace("$email", email)
                .replace("$accessLevelName", accessLevelName)
                .replace("$enabled", enabled)
                .replace("$protectedJson", protectedJson)
        ;
    }

}



