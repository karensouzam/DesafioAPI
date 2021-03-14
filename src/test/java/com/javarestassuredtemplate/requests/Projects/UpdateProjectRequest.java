package com.javarestassuredtemplate.requests.Projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class UpdateProjectRequest extends RequestRestBase{

    public UpdateProjectRequest(String id){
        requestService = "/api/rest/projects/"+ id;
        method = Method.PATCH;
    }

    public void setJsonBody(String id,
                            String nomeAlterado,
                            String enabled){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/UpdateProjectsJson.json");

        jsonBody = jsonBody.replace("$id", id)
                .replace("$nomeAlterado", nomeAlterado)
                .replace("$enabled", enabled)
                ;
    }

}
