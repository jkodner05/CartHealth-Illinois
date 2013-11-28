package com.carthealth.elasticsearch;

import java.util.List;

public class Stat {

	public String category;
	public String name;
	public List<County> counties;

	public Stat(String category, String name, List<County> counties)
	{
		this.category = category;
		this.name = name;
		this.counties = counties;
	}
}
