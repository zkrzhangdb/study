package com.example.demo;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoderTest {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader loader1 = new URLClassLoader(new URL[]{new URL("file:D:/loadertest/test1.jar")},  ClassLoderTest.class.getClassLoader());
        ClassLoader loader2 = new URLClassLoader(new URL[]{new URL("file:D:/loadertest/test2.jar")}, ClassLoderTest.class.getClassLoader());
                        String className = "te8st";

        // loader1
        System.out.print("test1.jar \t");
        Class clazz1 = Class.forName(className, true, loader1);
                System.out.println("HHHH");
        clazz1.getMethod("main", String[].class).invoke(null, (Object) null);

        System.out.println();

        // loader2
        System.out.print("test2.jar \t");
        Class clazz2 = Class.forName(className, true, loader2);
        clazz2.getMethod("main", String[].class).invoke(null, (Object) null);

        System.out.println();

        System.out.println("实例化后是否相等：" + clazz1.equals(clazz2));

    }

}
