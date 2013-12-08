package com.carthealth.model;

import java.util.List;

public class Stat {

	public String category;
	public String name;
	public String type;
	public List<County> counties;

	public Stat(String category, String name, String type, List<County> counties)
	{
		this.category = category;
		this.name = name;
		this.type = type;
		this.counties = counties;
	}
}
