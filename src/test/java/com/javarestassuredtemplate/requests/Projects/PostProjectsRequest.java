package com.javarestassuredtemplate.requests.Projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostProjectsRequest extends RequestRestBase{

    public PostProjectsRequest(){
        requestService = "/api/rest/projects/";
        method = Method.POST;
    }

    public void setJsonBody(String name,
                            String statusId,
                            String statusName,
                            String statusLabel,
                            String description,
                            String enabled,
                            String filePath,
                            String viewStateId,
                            String viewStateName,
                            String viewStateLabel){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostProjectsJson.json");

        jsonBody = jsonBody.replace("$name", name)
                .replace("$statusId", statusId)
                .replace("$statusName", statusName)
                .replace("$statusLabel", statusLabel)
                .replace("$description", description)
                .replace("$enabled", enabled)
                .replace("$filePath", filePath)
                .replace("$viewStateId", viewStateId)
                .replace("$viewStateName", viewStateName)
                .replace("$viewStateLabel", viewStateLabel)
                ;
    }

}
