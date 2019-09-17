package com.example.demo.test;

import com.sun.media.sound.ModelAbstractOscillator;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {



    public  static  void main(String[] args){
        // Person person = new Person();
        // System.out.println(person.hashCode());

        // Person  person1 = new Person();
        // person1.setName("张三");
        //
        //
        // Person  person2 = new Person();
        // person2.setName("张三");
        //
        // //set 不能重复
        // Set set = new HashSet();
        // set.add(person1);
        // set.add(person2);
        // System.out.println(set.size());
        // System.out.println("------------------------");
        // //key 唯一
        // Map map = new HashMap();
        // map.put(person1,"person1");
        // map.put(person2,"person2");
        // System.out.println(map.size());
        // System.out.println("-----------++++++++-------------");
        // System.out.println(person1.toString());
        // System.out.println(person1.toString());

          // System.out.println(2^3);

         Person person =new Person();
         Class c1 =Person.class;
         Class c2 =person.getClass();
        try {

            Class c3=Class.forName("com.example.demo.test.Person");
            Person person1 = (Person)c3.newInstance();
            System.out.println(c2==c3);
            person1.say();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(c1==c2);

    }
}
