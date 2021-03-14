package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetAnIssuesRequest extends RequestRestBase {

    public GetAnIssuesRequest(String id){
        requestService = "/api/rest/issues/" + id;
        method = Method.GET;
    }
}
