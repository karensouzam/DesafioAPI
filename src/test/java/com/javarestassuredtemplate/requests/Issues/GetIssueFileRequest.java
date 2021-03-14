package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetIssueFileRequest extends RequestRestBase {

    public GetIssueFileRequest(String id){
        requestService = "/api/rest/issues/"+id+"/files";
        method = Method.GET;
    }
}
