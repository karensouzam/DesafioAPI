package com.javarestassuredtemplate.bases;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.utils.ExtentReportsUtils;
import org.apache.http.annotation.NotThreadSafe;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.lang.reflect.Method;
import static com.javarestassuredtemplate.dbsteps.ConsultasDBSteps.*;

public abstract class TestBase {
    @BeforeSuite
    public void beforSuite(){
        new GlobalParameters();
        ExtentReportsUtils.createReport();
        //AutenticacaoSteps.gerarToken(GlobalParameters.AUTHENTICATOR_USER, GlobalParameters.AUTHENTICATOR_PASSWORD); //==> caso a geração de token deva ser feita quando iniciar a bateria de testes
        /*Karen
        String token = AutenticacaoSteps.gerarToken(GlobalParameters.AUTHENTICATOR_USER, GlobalParameters.AUTHENTICATOR_PASSWORD); //==> caso a geração de token deva ser feita quando iniciar a bateria de testes
        GlobalParameters.setToken(token);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        /*Karen*/

    }

    @BeforeMethod
    public void beforeTest(Method method){
        ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());
        //AutenticacaoSteps.gerarToken(GlobalParameters.AUTHENTICATOR_USER, GlobalParameters.AUTHENTICATOR_PASSWORD); ==> caso a geração de token deva ser feita antes de iniciar cada teste
        apagaDados();
        apagaDescricaoProblemas();
        apagaIssues();
        apagaIssuesNote();
        apagaIssuesNoteText();
        apagaVersionProject();

        insereDados();
        insereDescricaoProblemas();
        apagaUsuarios();
        //insereIssuesNote();
        //insereIssuesNoteText();
    }

    @AfterMethod
    public void afterTest(ITestResult result){

        ExtentReportsUtils.addTestResult(result);

        /*apagaDados();
        apagaDescricaoProblemas();
        apagaProblemas();*/

    }

    @AfterSuite
    public void afterSuite(){
        ExtentReportsUtils.generateReport();
       /* apagaDados();
        apagaDescricaoProblemas();
        apagaProblemas();*/
    }
}
