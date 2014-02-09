package com.example.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;

import com.carthealth.elasticsearch.ElasticSearch;

@Path("/bounce")
@Produces(MediaType.APPLICATION_JSON)
public class BounceService {

   @GET
    public List<String> get(@QueryParam("stats") String stat) {
	   ElasticSearch search = new ElasticSearch();
	  List<String> statList = new ArrayList<String>();
	   statList.add("PCT_DIABETES_ADULTS09");
	   return search.getStatsByCounty(statList);
    }
	
}
