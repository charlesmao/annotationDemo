package com.imooc.bean;

import com.imooc.myannotation.Column;
import com.imooc.myannotation.Table;

@Table
public class Student {
	
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private double score;

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	
	
}
