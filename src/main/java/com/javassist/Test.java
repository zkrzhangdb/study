package com.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test {



      public   ClassPool classPool ;

       public  Test(){
            this.classPool= ClassPool.getDefault();
       }

      public  static  void main(String[] args) throws  Exception{
          Test test =new Test();
          String  father = "com.entity.Person" ;
          String  son = "com.entity.Student" ;
          String  outPath="D:\\javassist";
          //父类生成
          test.makeClass(father,outPath);
          //子类生成
          test.makeClass(son,outPath);
          //设置子类的父类
          // test.setClassSub(father,son,outPath);
          //添加属性
          test.addField(son,"private String name ;",outPath);
         //增加方法
          String method = "  public  int getNum(int a){int num = 9 ;   num =  a+num ; return  num ; }";

          test.addMethod(son,method,outPath);
         //修改方法
          test.modifyMethod(son,outPath);
         //修改属性
          test.modifyFiled(son,outPath);
          //利用反射原理获取方法
          test.reflect(son,outPath);



          }



    /**
     * 生成类文件
     * @param className
     * @param outPath
     */
          public  void makeClass(String  className,String outPath){
          try {
              CtClass  person = classPool.makeClass(className);
              person.writeFile(outPath);
              System.out.println(className+"class文件生成成功！");
          }catch (Exception e ){
              e.printStackTrace();
           }
          }

    /**
     * 设置父类文件
     * @param father
     * @param son
     * @param outPath
     */
    public  void setClassSub(String father,String son,String outPath){
        try {
            CtClass  person = classPool.get(son);
            //解冻
            person.defrost();
            person.setSuperclass(classPool.get(father));
            person.writeFile(outPath);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public  void  addClassPath(String path){
        try {
            classPool.appendClassPath(path);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 增加字段
     * @param className
     * @param field
     */
    public  void  addField(String className,String field ,String outPath) {

        try {
            CtClass person = classPool.get(className);
            person.defrost();
            CtField   ctField = CtField.make(field,person);
            person.addField(ctField);
            person.writeFile(outPath);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 增加方法
     * @param className
     * @param method
     * @param outPath
     */
    public  void  addMethod(String className,String method ,String outPath) {

        try {
            CtClass person = classPool.get(className);
            person.defrost();
            CtMethod ctMethod = CtMethod.make(method,person);
            person.addMethod(ctMethod);
            person.writeFile(outPath);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改方法
     * @param className
     * @param outPath
     */
    public  void  modifyMethod(String className ,String outPath) {

        try {
            CtClass person = classPool.get(className);
            person.defrost();
            CtMethod ctMethod =person.getDeclaredMethod("getNum",new CtClass[]{CtClass.intType});
            ctMethod.insertBefore(" System.out.println(\"执行方法前。。。。\");");
            ctMethod.insertAfter(" System.out.println(\"执行方法后。。。。\");");
            person.writeFile(outPath);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改字段
     * @param className
     * @param outPath
     */
    public  void  modifyFiled(String className ,String outPath) {

        try {
            CtClass person = classPool.get(className);
            person.defrost();

             CtField ctField = person.getDeclaredField("name") ;
             ctField.setModifiers(Modifier.PUBLIC);
             person.writeFile(outPath);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     public  void  reflect(String son,String outPath ){


         try {

             CtClass person = classPool.get(son);
             Class<?>  myclass = person.toClass();
             Object  object = myclass.newInstance();
             Method method =myclass.getMethod("getNum");
             method.invoke(object);

         } catch (Exception e) {
             e.printStackTrace();
         }

     }



    }
