package com.example.services;

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
    public List<String> get(@QueryParam("stats") List<String> stats) {
	   ElasticSearch search = new ElasticSearch();
	   return search.getStatsByCounty(stats);
    }
	
}
