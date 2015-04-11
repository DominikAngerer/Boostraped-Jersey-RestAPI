package com.dominikangerer.gson.provider.v1.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dominikangerer.gson.provider.v1.container.ResourceContainer;
import com.dominikangerer.gson.provider.v1.model.Resource;

@Path("/v1/resources")
public class ResourceController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response get() {
		return ResourceContainer.ok(Resource.getResources());
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) {
		return ResourceContainer.ok(Resource.getResource());
	}
}
