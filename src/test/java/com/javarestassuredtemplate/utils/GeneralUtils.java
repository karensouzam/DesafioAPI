package com.javarestassuredtemplate.utils;

import org.testng.ITestResult;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class GeneralUtils {

    public static String readFileToAString(String path){
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, StandardCharsets.ISO_8859_1);
    }

    public static String getNowDate(String mask){
        DateFormat dateFormat = new SimpleDateFormat(mask);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getMethodNameByLevel(int level){
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        return stackTrace[level].getMethodName();
    }

    public static String getAllStackTrace(ITestResult result){
        String allStackTrace = "";

        for(StackTraceElement stackTrace : result.getThrowable().getStackTrace()){
            allStackTrace = allStackTrace + "<br>" + stackTrace.toString();
        }

        return allStackTrace;
    }

    public static String formatJson(final String json) {
        final int indent_width = 4;
        final char[] chars = json.toCharArray();
        final String newline = System.lineSeparator();

        String ret = "";
        boolean begin_quotes = false;

        for (int i = 0, indent = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '\"') {
                ret += c;
                begin_quotes = !begin_quotes;
                continue;
            }

            if (!begin_quotes) {
                switch (c) {
                    case '{':
                    case '[':
                        ret += c + newline + String.format("%" + (indent += indent_width) + "s", "");
                        continue;
                    case '}':
                    case ']':
                        ret += newline + ((indent -= indent_width) > 0 ? String.format("%" + indent + "s", "") : "") + c;
                        continue;
                    case ':':
                        ret += c + " ";
                        continue;
                    case ',':
                        ret += c + newline + (indent > 0 ? String.format("%" + indent + "s", "") : "");
                        continue;
                    default:
                        if (Character.isWhitespace(c)) continue;
                }
            }

            ret += c + (c == '\\' ? "" + chars[++i] : "");
        }

        return ret;
    }

    public static int getId(){
        int i = 1 + (int) (Math.random() * 100);
        return i;
    }

    public static Long getNumeroAleatorio(){
        Long i = 1 + (long) (Math.random() * 10000);
        return i;
    }
    /*public static String leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);
               // return linha;

            } else
                break;
            linha = buffRead.readLine();
           // return linha;
        }
        buffRead.close();
        return linha;

    }

    public static  List<String> leitor2(String arquivo) throws IOException {
        Path path = Paths.get(arquivo);

        List<String> linhasArquivo = Files.readAllLines(path);
        for (String linha : linhasArquivo) {
            System.out.println( linha );
        }
        return linhasArquivo;
    }*/
}
