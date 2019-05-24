package com.bbc.spring.demo.ioc.test;

import com.bbc.spring.demo.ioc.annotation.po.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//spring 整合 junit完成单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-ioc-anno.xml")
public class TestIoCAnno {
	
	@Autowired
	private Student student;

	@Test
	public void testInitApplicationContext() throws Exception {
		System.out.println(student);
	}

}
