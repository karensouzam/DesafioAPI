package com.javarestassuredtemplate.requests.Projects;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class UpdateSubProjectRequest extends RequestRestBase{

    public UpdateSubProjectRequest(String id, String subProjectId){
        requestService = "/api/rest/projects/" +id + "/subprojects/" + subProjectId;
        method = Method.PATCH;
    }

    public void setJsonBody(String nome,
                            String inherit_parent){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/PostSubProjectsJson.json");

        jsonBody = jsonBody.replace("$nome", nome)
                .replace("$inherit_parent", inherit_parent)
        ;
    }

}
