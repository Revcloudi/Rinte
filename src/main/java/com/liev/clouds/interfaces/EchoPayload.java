package com.liev.clouds.interfaces;

import org.apache.commons.lang3.StringUtils;
import javassist.ClassPool;
import javassist.CtClass;


public interface EchoPayload<T> {
    CtClass genPayload(ClassPool paramClassPool) throws Exception;

    public static class Utils
    {
        public static Class<? extends EchoPayload> getPayloadClass(String className) throws ClassNotFoundException {
            Class<? extends EchoPayload> clazz = null;
            try {
                clazz = (Class)Class.forName("com.liev.clouds.payload.shiro.echo." + StringUtils.capitalize(className));
            } catch (ClassNotFoundException e1) {
                clazz = (Class)Class.forName("com.liev.clouds.payload.shiro.plugins." + StringUtils.capitalize(className));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return clazz;
        }
    }
}



