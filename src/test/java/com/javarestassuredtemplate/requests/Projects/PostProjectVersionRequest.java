package com.javarestassuredtemplate.requests.Projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostProjectVersionRequest extends RequestRestBase{

    public PostProjectVersionRequest(String id){
        requestService = "/api/rest/projects/"+ id +"/versions/";
        method = Method.POST;
    }

    public void setJsonBody(String name,
                            String description,
                            String released,
                            String obsolete,
                            String timestamp
                            ){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostProjectVersionJson.json");

        jsonBody = jsonBody.replace("$name", name)
                .replace("$description", description)
                .replace("$released", released)
                .replace("$obsolete", obsolete)
                .replace("$timestamp", timestamp)
                ;
    }

}
