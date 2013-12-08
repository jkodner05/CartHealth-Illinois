package com.carthealth.elasticsearch;

public class County 
{
	public String county;
	public String state;
	public String fips;
	public float value;
	
	public County(String county, String state, String fips, float value)
	{
		this.county = county;
		this.state = state;
		this.fips = fips;
		this.value = value;
	}
}
