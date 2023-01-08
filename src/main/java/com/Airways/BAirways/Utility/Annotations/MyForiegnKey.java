package com.Airways.BAirways.Utility.Annotations;

import com.Airways.BAirways.Entity.DBTable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyForiegnKey {
    public Class table();
    public String coloumn();
}
