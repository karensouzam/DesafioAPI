package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetAnIssueForProjectsRequest extends RequestRestBase {

    public GetAnIssueForProjectsRequest(String id){
        requestService = "/api/rest/issues?project_id=" + id;
        method = Method.GET;
    }
}
