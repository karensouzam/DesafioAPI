package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;
import java.util.ArrayList;

public class ConsultasDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/javarestassuredtemplate/queries/";

    //Manipulação dados projeto
    public static ArrayList<String> insereDadosProjeto(String nome){
        ArrayList<String> dadosProjeto;
        String query = GeneralUtils.readFileToAString(queriesPath + "insereProjetosQuery.sql").replace("$nome", nome);
        dadosProjeto = DBUtils.executeUpdate(query);
        return dadosProjeto;
    }

    public static void apagaDadosProjeto(){
        String query3 = GeneralUtils.readFileToAString(queriesPath + "apagaProjetosQuery.sql");
        DBUtils.executeUpdate(query3);
    }

    public static ArrayList<String> retornaProjetos(String nome){
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaProjetosQuery.sql").replace("$nome", nome);
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

    public static ArrayList<String> retornaProjetos(){
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaProjetosQuery.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

    public static ArrayList<String> retornaProjetoAlterado(String nomeAlterado){
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaProjetoAlteradoQuery.sql").replace("$nomeAlterado", nomeAlterado);
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

    public static void apagaVersionProject(){
        String query = GeneralUtils.readFileToAString(queriesPath + "apagaVersionProjectQuery.sql");
        DBUtils.executeUpdate(query);
    }

    //Manipulação dados Issues
    public static void apagaDescricaoIssue(){
        String query = GeneralUtils.readFileToAString(queriesPath + "apagaDescricaoIssuesQuery.sql");
        DBUtils.executeUpdate(query);
    }

    public static void insereDescricaoIssue(){
        String query = GeneralUtils.readFileToAString(queriesPath + "insereDescricaoIssuesQuery.sql");
        DBUtils.executeUpdate(query);
    }

    public static ArrayList<String> retornaDescricaoIssue(){
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaDescricaoIssueQuery.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

    public static void apagaIssues(){
        String query = GeneralUtils.readFileToAString(queriesPath + "apagaIssuesQuery.sql");
        DBUtils.executeUpdate(query);
    }

    public static void apagaIssuesNote(){
        String query = GeneralUtils.readFileToAString(queriesPath + "apagaIssuesNoteQuery.sql");
        DBUtils.executeUpdate(query);
    }

    public static void apagaIssuesNoteText(){
        String query = GeneralUtils.readFileToAString(queriesPath + "apagaIssuesNoteTextQuery.sql");
        DBUtils.executeUpdate(query);
    }

    public static void insereIssues(String id, String bugTextId){
        String query = GeneralUtils.readFileToAString(queriesPath + "insereIssueQuery.sql")
                .replace("$id", id)
                .replace("$bugTextId", bugTextId);
        DBUtils.executeUpdate(query);
    }

    public static ArrayList<String> retornaIssues() {
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaIssuesQuery.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

    public static ArrayList<String> retornaIssuesEspecifico(String nomeProjeto) {
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaIssuesEspecificoQuery.sql").replace("$nomeProjeto", nomeProjeto);
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

    //Manipulação dados Users
    public static void apagaUsuarios(){
        String query = GeneralUtils.readFileToAString(queriesPath + "apagaUsuariosQuery.sql");
        DBUtils.executeUpdate(query);
    }

    public static void insereUsuarioDesprotegido(){
        String query = GeneralUtils.readFileToAString(queriesPath + "insereUsuarioDesprotegidoQuery.sql");
        DBUtils.executeUpdate(query);
    }

    public static ArrayList<String> retornaUsuarios(){
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaUsuariosQuery.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }

    public static ArrayList<String> retornaUsuarioEspecifico(){
        ArrayList<String> dados;
        String query = GeneralUtils.readFileToAString(queriesPath + "retornaUsuarioEspecificoQuery.sql");
        dados = DBUtils.getQueryResult(query);
        return dados;
    }
}
