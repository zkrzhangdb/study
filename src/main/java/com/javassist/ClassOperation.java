package com.javassist;

import javassist.*;

public class ClassOperation {


    private String name;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public  static  void main(String[] args) throws  Exception{

        ClassOperation classOperation=new ClassOperation();
            classOperation.CreateClass();
        classOperation.getClasss();

            }


    public   void  getClasss() throws  Exception {
        ClassPool classPool =ClassPool.getDefault();
        //路径追加
        classPool.appendClassPath("D://javassist");
        CtClass ctClass = classPool.get("com.javassist.Person");
        ctClass.defrost();//解冻
        CtField  test =CtField.make("private String test;",ctClass);
        ctClass.addField(test);
        ctClass.writeFile("D://javassist");
    }

     public   void  CreateClass() {
         try {
             //ClassPool --javassist  类池
             ClassPool classPool =ClassPool.getDefault();
             //CtClass 类对象
             CtClass person = classPool.makeClass("com.javassist.Person");

             String[] ss= System.getProperty("java.class.path").split(";");
             for(int i=0 ;i<ss.length;i++)
             {
                 System.out.println(ss[i]);//系统的classpaht路径

             }
             //增加成员变量 name
             CtField  name =CtField.make("private String name;",person);
             person.addField(name);
             //增加成员变量 age
             CtField  age =CtField.make("private String age;",person);
             person.addField(age);
             //增加方法  getName setName
             CtMethod   getName = CtMethod.make("public String getName() {return name;}",person);
             CtMethod   setName = CtMethod.make("public void setName(String name) {this.name = name;}",person);
             person.addMethod(getName);
             person.addMethod(setName);
             //增加方法getAge setAge
             CtMethod   getAge = CtMethod.make("public String getAge() {return age;}",person);
             CtMethod   setAge = CtMethod.make("public void setAge(String age) {this.age = age;}",person);
             person.addMethod(getAge);
             person.addMethod(setAge);
             //增加构造方法
             CtConstructor ctConstructor = new CtConstructor(new CtClass[]{classPool.get("java.lang.String"),classPool.get("java.lang.String")},person);
             ctConstructor.setBody("{ this.age=age;this.name=name;}");
             person.addConstructor(ctConstructor);
             //生成类文件
             person.writeFile("D://javassist");
             System.out.println("文件生成成功。。。。");
             person.toBytecode();
             // CtClass cc =classPool.get("com.test");
             // cc.writeFile("D://javassist");

             ClassPool classPool2 =ClassPool.getDefault();
             System.out.println(classPool==classPool2);



         }catch (Exception e){
             e.printStackTrace();
         }
         System.out.println("");

     }


}
