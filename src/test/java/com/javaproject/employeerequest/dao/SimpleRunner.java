package com.javaproject.employeerequest.dao;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRunner {
    public static void main(String[] args) {
        SimpleRunner sr = new SimpleRunner();
        
        sr.runTest();
    }

    private void runTest() {
        try {
            Class cl = Class.forName("com.javaproject.employeerequest.dao.DictionaryDaoImplTest");

            Constructor cstr = cl.getConstructor();
            Object entity = cstr.newInstance();

            Method[] methods = cl.getMethods();
            for (Method m : methods)
            {
                Test annotation = m.getAnnotation(Test.class);
                if (annotation != null)
                    m.invoke(entity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
