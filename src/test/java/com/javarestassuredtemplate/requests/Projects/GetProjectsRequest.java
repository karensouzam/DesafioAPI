package com.javarestassuredtemplate.requests.Projects;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetProjectsRequest extends RequestRestBase{

        public GetProjectsRequest(){
            requestService = "/api/rest/projects/";
            method = Method.GET;
        }

}
