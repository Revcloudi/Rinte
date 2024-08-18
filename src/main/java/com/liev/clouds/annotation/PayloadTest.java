package com.liev.clouds.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PayloadTest {
  String skip() default "";
  
  String precondition() default "";
  
  String harness() default "";
  
  String flaky() default "";
}


