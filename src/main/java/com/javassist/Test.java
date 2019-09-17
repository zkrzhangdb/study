package com.javassist;

import javassist.ClassPool;
import javassist.CtClass;

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
          test.setClassSub(father,son,outPath);

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

    public  void  addClassPath(String pass){

    }

}
