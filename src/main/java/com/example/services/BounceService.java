package com.example.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;

@Path("/bounce")
@Produces(MediaType.APPLICATION_JSON)
public class BounceService {

   @GET
    public String get(@QueryParam("stat") String stat) {
        return "hi there, client! You requested statistics for: " + stat + ". Yay! You get nothing back.";
    }
	
}
