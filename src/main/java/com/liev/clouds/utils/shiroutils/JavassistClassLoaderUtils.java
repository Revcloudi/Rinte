package com.liev.clouds.utils.shiroutils;

/**
 * @author Revcloud
 * @since 2024/8/18 2:11
 */
public class JavassistClassLoaderUtils extends ClassLoader{
    public JavassistClassLoaderUtils(){
        super(Thread.currentThread().getContextClassLoader());
    }
}
