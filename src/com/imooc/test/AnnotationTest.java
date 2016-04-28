package com.imooc.test;

import com.imooc.bean.Student;
import com.imooc.bean.User;
import com.imooc.dao.Filter;

public class AnnotationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		User user1 = new User();
		user1.setId(1);
		
		User user2 = new User();
		user2.setUserName("xiaoming");
		
		User user3 = new User();
		user3.setEmail("aaa@126.com, bbb@126.com, ccc@126.com");
		
		System.out.println(Filter.getSql(user1));
		System.out.println(Filter.getSql(user2));
		System.out.println(Filter.getSql(user3));
		
		Student stu = new Student();
		stu.setName("xiaohong");
		System.out.println(Filter.getSql(stu));
	}

}
