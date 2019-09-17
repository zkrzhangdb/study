package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

public class MyClassLoader  extends  ClassLoader{



    //class文件路径
    private String classPath;
    //类的全限定名
    private Set<String> ownClass;

    public MyClassLoader (String classPath, Set<String> ownClass) {
        super();
        this.classPath = classPath;
        this.ownClass = ownClass;
    }

    public void setClass(String name){
        ownClass.add(name);
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        //判断这个类有没有被加载
        Class<?> clazz = findLoadedClass(name);
        //如果没有被加载且是我们自定义的类，那就自己加载
        if (clazz == null && ownClass.contains(name)) {
            clazz = findClass(name);
            if (clazz != null) {
                return clazz;
            }
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //获取class文件字节数组
        byte[] data = new byte[0];
        try {
            data = getClassByte(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data == null) {
            throw new NullPointerException("this class not found");
        }
        //将二进制数据转换成class对象
        return defineClass(name,data,0,data.length);
    }

    private byte[] getClassByte(String name) throws Exception {

        byte[] data = null;
        String className = name.substring(name.lastIndexOf(".") + 1) + ".class";
        File file = new File(classPath+className);
        if (!file.exists()) return data;

        FileInputStream fis = new FileInputStream(file);
        byte[] buff = new byte[1024 * 2];
        int len ;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = fis.read(buff)) != -1) {
            bos.write(buff,0,len);
        }
        data = bos.toByteArray();
        fis.close();
        bos.close();
        return data;
    }


}
