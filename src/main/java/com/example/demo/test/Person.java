package com.example.demo.test;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Objects;

public class Person {

    static {

        System.out.println("父类静态代码块...");

    }

    {

        System.out.println("父类普通代码块");

    }



    public  Person(){
        System.out.println("父类类构造函数....");

    }



 public  void  say(){

        System.out.println("我要说话了 。。。。。");

 }


    private  String name;
    private  String age;

    //
    // public String getName() {
    //     return name;
    // }
    //
    // public void setName(String name) {
    //     this.name = name;
    // }
    //
    // public String getAge() {
    //     return age;
    // }
    //
    // public void setAge(String age) {
    //     this.age = age;
    // }
    //
    // @Override
    // public boolean equals(Object obj) {
    //
    //     Person person = (Person) obj;
    //     if(this.getName().equals(person.getName())){
    //         return  true ;
    //     }else
    //     {
    //         return  false;
    //     }
    //
    //
    // }

    // @Override
    // public int hashCode() {
    //     int number =31;
    //     System.out.println("我被调用了.....");
    //     return  this.name.length()*number;
    //
    // }


    // @Override
    // public int hashCode() {
    //     System.out.println("name:"+name+Objects.hash(name));
    //     return Objects.hash(name);
    //
    // }




}


