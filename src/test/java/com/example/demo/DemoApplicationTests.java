package com.example.demo;

import com.example.demo.test.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

public class DemoApplicationTests {


	@Test
	public void contextLoads() {

		System.out.println("new。。。。1");
		Student student  = new Student();
		// System.out.println("b:==="+student.b);
		System.out.println("new。。。。2");
		Student student2  = new Student();



	}

}
