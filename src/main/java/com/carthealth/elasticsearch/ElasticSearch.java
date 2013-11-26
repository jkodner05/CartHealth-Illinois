package com.carthealth.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.core.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.*;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class ElasticSearch {

	public static final String DEFAULT_CONNECTION = "http://m1c7vj1c:l696mzd8bgh1ok3k@ash-1503347.us-east-1.bonsai.io";
	public static final String DEFAULT_INDEX = "stats";
	public static final String DEFAULT_TYPE = "usda";
	
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

	public List<String> getColorsByStat(String statName)
	{
		List<String> javascriptCalls = new ArrayList<String>();
		Search search = new Search.Builder("{name:" + statName + "}")
        // multiple index or types can be added.
        .addIndex("INDEX")
        .addIndex("TYPE")
        .build();

		try {
			JestResult result = client.execute(search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Okay?");
		return javascriptCalls;
	}
	
	
	public static void main(String[] args) {
		ElasticSearch es = ElasticSearch.getInstance();
		es.getColorsByStat("LACCESS_HHNV10");
		// TODO Auto-generated method stub

	}

}
