package com.javarestassuredtemplate.steps;

import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;

public class GeralSteps {
    public GeralSteps() {
    }

    public static void insereIssues(){
        System.out.println(ConsultasDBSteps.retornaProjetos().get(0));
        ConsultasDBSteps.insereIssues(ConsultasDBSteps.retornaProjetos().get(0), ConsultasDBSteps.retornaDescricaoIssue().get(0));


    }
}
