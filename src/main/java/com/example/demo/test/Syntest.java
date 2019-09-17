package com.example.demo.test;

import org.springframework.web.bind.annotation.RequestMapping;

public class Syntest {

    boolean flag =false;
    volatile int  result =0 ;

    volatile int number=1;

    public  static  void main(String[] args) throws  Exception{

       for(int i=0;i<10 ;i++)
       {
           Syntest syntest  =new Syntest();
           syntest.new MyThread(true).start();
/*
           Thread.sleep(1000);
*/
           syntest.new MyThread(false).start();
       }



    }

    public   synchronized void read(){
        flag =true;
        number=2;
    }
    public  synchronized   void write(){
        if (flag){
            result = number*3;
        }
        System.out.println("result:"+result);
    }



    class  MyThread extends   Thread{

           public  boolean flag;

             public  MyThread(Boolean flag){

                 this.flag=flag;
             }
                @Override
                public void run() {
              if(flag){
               write();
              }else {
                  read();
              }

                }
            }
}
