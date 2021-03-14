package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostIssueNoteRequest extends RequestRestBase {

    public PostIssueNoteRequest(String id){
        requestService = "/api/rest/issues/" + id + "/notes";
        method = Method.POST;
    }

    public void setJsonBody(String text,
                            String name){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostIssueNoteJson.json");

        jsonBody = jsonBody.replace("$text", text)
                .replace("$name", name)
        ;
    }
}
