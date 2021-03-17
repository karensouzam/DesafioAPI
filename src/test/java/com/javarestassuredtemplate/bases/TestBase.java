package com.javarestassuredtemplate.bases;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.dbsteps.ConsultasDBSteps;
import com.javarestassuredtemplate.utils.ExtentReportsUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.lang.reflect.Method;

public abstract class TestBase {
    @BeforeSuite
    public void beforSuite(){
        new GlobalParameters();
        ExtentReportsUtils.createReport();
    }

    @BeforeMethod
    public void beforeTest(Method method){ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());}

    @AfterMethod
    public void afterTest(ITestResult result){
        ExtentReportsUtils.addTestResult(result);
    }

    @AfterSuite
    public void afterSuite(){
        ExtentReportsUtils.generateReport();
        ConsultasDBSteps.apagaDadosProjeto();
        ConsultasDBSteps.apagaIssues();
        ConsultasDBSteps.apagaDescricaoIssue();
        ConsultasDBSteps.apagaIssuesNote();
        ConsultasDBSteps.apagaUsuarios();
        ConsultasDBSteps.apagaIssuesNoteText();
        ConsultasDBSteps.apagaVersionProject();
    }
}
