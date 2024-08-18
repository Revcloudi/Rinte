package com.liev.clouds.payload.shiro;

import com.sun.xml.internal.ws.util.StringUtils;



public interface ObjectPayload<T> { T getObject(Object paramObject) throws Exception;

    public static class Utils {
        public static Class<? extends ObjectPayload> getPayloadClass(String className) {
            Class<? extends ObjectPayload> clazz = null;
            try {
                clazz = (Class)Class.forName("com.liev.clouds.payload.shiro." + StringUtils.capitalize(className));
            } catch (Exception exception) {}

            return clazz;
        }
    }
}

