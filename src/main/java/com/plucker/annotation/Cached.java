package com.plucker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author dushyantmistry
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cached {
  long maximumSize() default 100;

  boolean expireAfterWrite() default false;

  boolean expireAfterAccess() default true;

  long duration() default 20;

  TimeUnit durationUnit() default TimeUnit.MINUTES;

  boolean recordStats() default false;

  boolean staticClass() default false;

  String staticInitializerName() default "getInstance";
}

