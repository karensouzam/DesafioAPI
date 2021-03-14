package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetAllIssuesRequest extends RequestRestBase {

    public GetAllIssuesRequest(){
        requestService = "/api/rest/issues?page_size=10&page=1";
        method = Method.GET;
    }
}
