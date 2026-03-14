package com.bank.app.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 1. @Target defines WHERE this annotation can be placed.
// ElementType.TYPE means it can be placed on classes, interfaces, or enums.
@Target(ElementType.TYPE)

// 2. @Retention defines HOW LONG the annotation is kept around.
// RUNTIME means Spring can read this annotation while the application is running.
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresLiveDatabase {
    // You can define attributes here if you want (e.g., String value() default "";)
    // But for a simple marker annotation, we leave it empty.
}