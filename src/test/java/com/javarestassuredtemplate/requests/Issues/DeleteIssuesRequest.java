package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DeleteIssuesRequest extends RequestRestBase {

        public DeleteIssuesRequest(String id){
            requestService = "/api/rest/issues/"+ id;
            method = Method.DELETE;
        }
    }

