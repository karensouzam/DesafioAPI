package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostIssuesRequest extends RequestRestBase {

    public PostIssuesRequest(){
        requestService = "/api/rest/issues/";
        method = Method.POST;
    }

    public void setJsonBody(String summary,
                            String description,
                            String categoryName,
                            String projectName){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostIssueJson.json");

        jsonBody = jsonBody.replace("$summary", summary)
                .replace("$description", description)
                .replace("$categoryName", categoryName)
                .replace("$projectName", projectName)
        ;
    }
}
