package com.javarestassuredtemplate.requests.Projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetOnlyProjectRequest extends RequestRestBase{

        public GetOnlyProjectRequest(String id){
            requestService = "/api/rest/projects/"+ id;
            method = Method.GET;
        }
}
