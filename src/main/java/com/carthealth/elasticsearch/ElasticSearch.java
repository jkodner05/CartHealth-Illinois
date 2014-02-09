package com.carthealth.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.core.Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ElasticSearch {

	public static final String DEFAULT_CONNECTION = "http://m1c7vj1c:l696mzd8bgh1ok3k@ash-1503347.us-east-1.bonsai.io";
	public static final String DEFAULT_INDEX = "stats";
	public static final String DEFAULT_TYPE = "usda";
	
	public static final String SATURATION = "85%";
	public static final String LIGHTNESS = "50%";
	
	private String CONNECTION;
	private String INDEX;
	private String TYPE;
	private JestClient client;
	
	
	public static ElasticSearch getInstance()
	{
		return new ElasticSearch();
	}
	
	public ElasticSearch()
	{
		CONNECTION = "http://ash-1503347.us-east-1.bonsai.io/";
		INDEX = DEFAULT_INDEX;
		TYPE = DEFAULT_TYPE;
		
		 ClientConfig clientConfig = new ClientConfig.Builder(CONNECTION).multiThreaded(true).build();
		 JestClientFactory factory = new JestClientFactory();
		 factory.setClientConfig(clientConfig);
		 client = factory.getObject();

		//client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(CONNECTION, 9300));
	}

	public List<County> getCountiesFromResult(JestResult result)
	{
		List<County> counties = new ArrayList<County>();
		List<Stat> stats = result.getSourceAsObjectList(Stat.class);	
		
		for(Stat stat : stats)
		{
			counties.addAll(stat.counties);
		}
		return counties;
	}
	
	public Map<String,List<String>> getStatsByCounty(List<String> statNames)
	{
		Map<String,List<String>> output = new HashMap<String,List<String>>();
		for(String statName : statNames)
			output.put(statName,getStatsByCounty(statName));
		return output;
	}
	
	public String getHSL(float min, float max, float val)
	{
		float span = max - min;
		float portion = val - min;
		int h = (int)((portion/span)*360.0);
		return h + "," + SATURATION + "," + LIGHTNESS;
	}
	
	public List<String> getColorsAndValues(List<County> counties)
	{
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		
		for(County county : counties)
		{
			if(county.value < min)
				min = county.value;
			if(county.value > max)
				max = county.value;
		}
		
		List<String> data = new ArrayList<String>();
		
		for(County county : counties)
		{
			String hsl = getHSL(min,max,county.value);
			data.add("{\"" + county.county + "\": {color:\""+hsl+"\", value:\""+county.value+"\"}}");
		}
		return data;
	}
	
	public List<String> getStatsByCounty(String statName)
	{
		List<String> output = new ArrayList<String>();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.queryString("name:" + statName));
		Search search = new Search.Builder(searchSourceBuilder.toString())
        // multiple index or types can be added.
        .addIndex(INDEX)
        .addType(TYPE)
        .build();

		try {
			JestResult result = client.execute(search);
			List<County> counties = getCountiesFromResult(result);
			output = getColorsAndValues(counties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	//For testing
	public static void main(String[] args) {
		ElasticSearch es = ElasticSearch.getInstance();
		es.getStatsByCounty("LACCESS_HHNV10");
		// TODO Auto-generated method stub

	}

}
