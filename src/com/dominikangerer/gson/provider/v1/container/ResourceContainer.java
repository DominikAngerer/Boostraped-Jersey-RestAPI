package com.dominikangerer.gson.provider.v1.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;

import com.dominikangerer.gson.provider.v1.model.Resource;

public class ResourceContainer {
	private class MetaContainer {
		private int total;

		public MetaContainer total(int total) {
			this.setTotal(total);
			return this;
		}

		@SuppressWarnings("unused")
		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	}

	private MetaContainer meta;

	public Resource resource;

	public Collection<Resource> resources;

	public ResourceContainer resource(Resource resource) {
		setResource(resource);
		return this;
	}

	public ResourceContainer resources(Collection<Resource> resources) {
		setResources(resources);
		return this;
	}

	public ResourceContainer meta(MetaContainer meta) {
		this.setMeta(meta);
		return this;
	}

	public ResourceContainer(Resource resource) {
		setResource(resource);
	}

	public ResourceContainer(Collection<Resource> resources) {
		setResources(resources);
	}

	public MetaContainer getMeta() {
		return meta;
	}

	public void setMeta(MetaContainer meta) {
		this.meta = meta;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		if (resources != null) {
			throw new RuntimeException(
					"Can't set a singleValue for a multiValue");
		}
		this.resource = resource;
		this.meta(null);
	}

	public Collection<Resource> getResources() {
		return resources;
	}

	public void setResources(Collection<Resource> resources) {
		if (resource != null) {
			throw new RuntimeException(
					"Can't set a multivalue for a singleValue");
		}
		this.resources = resources;
		if (resources != null) {
			this.meta(new MetaContainer().total(this.getResources().size()));
		} else {
			this.meta(new MetaContainer().total(0));
		}
	}

	public boolean isSingleValue() {
		if (resource != null && resources == null) {
			return true;
		} else if (resource != null && resources != null) {
			throw new RuntimeException(
					"Single & mutlivalues are set in container!");
		}
		return false;
	}

	public boolean isNotSingleValue() {
		if (resource == null && resources != null) {
			return true;
		}
		return false;
	}

	public boolean isMultiValue() {
		if (resources != null && resource == null) {
			return true;
		}
		return false;
	}

	public boolean isNotMultiValue() {
		if (resources == null && resource != null) {
			return true;
		}
		return false;
	}

	public void addResources(Resource resource) {
		if (getResources() != null) {
			getResources().add(resource);
		} else {
			setResources(new ArrayList<Resource>());
			this.addResources(resource);
		}
	}

	public static Response ok(ArrayList<Resource> resources) {
		return Response.ok(new ResourceContainer(resources)).build();
	}
	
	public static Response ok(List<Resource> resources) {
		return Response.ok(new ResourceContainer(resources)).build();
	}
	
	public static Response ok(Collection<Resource> resources) {
		return Response.ok(new ResourceContainer(resources)).build();
	}

	public static Response ok(Resource resource) {
		return Response.ok(new ResourceContainer(resource)).build();
	}

	public static Response deleted() {
		return Response.noContent().build();
	}
	
	public static Response updated() {
		return Response.noContent().build();
	}
	
	public static Response updated(Resource resource) {
		return Response.ok(new ResourceContainer(resource)).build();
	}

	public static Response created(Resource resource) {
		return Response.status(Response.Status.CREATED).entity(new ResourceContainer(resource)).build();
	}
}
