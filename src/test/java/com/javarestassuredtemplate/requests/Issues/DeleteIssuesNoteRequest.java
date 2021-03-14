package com.javarestassuredtemplate.requests.Issues;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DeleteIssuesNoteRequest extends RequestRestBase {

        public DeleteIssuesNoteRequest(String id, String idNote){
            requestService = "/api/rest/issues/"+ id + "/notes/" + idNote;
            method = Method.DELETE;
        }
    }

