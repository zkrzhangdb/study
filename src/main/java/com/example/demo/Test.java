package com.example.demo;

import com.oracle.webservices.internal.api.EnvelopeStyle;
import sun.misc.Launcher;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] arg){
        // //AppClassLoader
        // ClassLoader c  = Test.class.getClassLoader();  //获取Test类的类加载器
        // System.out.println(c);
        // //ExtClassLoader
        // ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器
        // System.out.println(c1);
        // //BootstrapClassLoader C++语言实现所以为空
        // ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器
        // System.out.println(c2);
        //
        // // rt.jar
        // ClassLoader classLoader= EnvelopeStyle.class.getClassLoader();
        // System.out.println(classLoader);
        //
        // ClassLoader Launcherloader = Launcher.class.getClassLoader();
        // System.out.println(Launcherloader);
        //
        //
        // System.out.println(System.getProperty("sun.boot.class.path"));


     //    String var0 = System.getProperty("java.ext.dirs");
     //    System.out.println("测试:"+var0);
     //
     //
     //    String var1 =System.getProperty("java.class.path");
     //    ClassLoader classLoader = Test.class.getClassLoader();
     // System.out.println(classLoader);
     //    System.out.println(   classLoader.getSystemClassLoader());
     //
     //    System.out.println("测试:"+var1);



            while (true) {

                String classPath = "D:\\class\\";
                String className = "LoadTest";

                Set<String> set = new HashSet<>();
                set.add(className);
                MyClassLoader loader = new MyClassLoader (classPath,set);
                System.out.println("当前loder="+loader);

                try {
                    Class<?> clazz = loader.loadClass(className);
                    Object o = clazz.newInstance();
                    clazz .getMethod("loadUser").invoke(o);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }



}
