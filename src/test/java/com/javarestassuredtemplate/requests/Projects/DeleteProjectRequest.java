package com.javarestassuredtemplate.requests.Projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DeleteProjectRequest extends RequestRestBase{

        public DeleteProjectRequest(String id){
            requestService = "/api/rest/projects/"+ id;
            method = Method.DELETE;
        }
}
