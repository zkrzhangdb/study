
package com.example.demo.test;

public class Student extends  Person {


     static {

         System.out.println("子类静态代码块");
     }

    {
        System.out.println("子类普通代码块");

    }

    private  static  int a = 0;
    public     int b= 0;
    public  Student (){
        // b=8;
        System.out.println("子类构造函数");
    }


        public  static  void main(String[] args){

         for(String ars:args )
         {
            System.out.println(ars);
         }
            }




}
