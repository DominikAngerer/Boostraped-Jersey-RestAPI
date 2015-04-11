package com.dominikangerer.gson.provider.v1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Resource {
	private String id;

	private String data;

	private Long value;

	private Date created;

	private boolean booleanData;
	
	public Resource() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Resource withId(String id) {
		this.id = id;
		return this;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Resource withData(String data) {
		this.data = data;
		return this;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Resource withValue(Long value) {
		this.value = value;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Resource withCreated(Date created) {
		this.created = created;
		return this;
	}

	public boolean getBooleanData() {
		return booleanData;
	}

	public void setBooleanData(boolean booleanData) {
		this.booleanData = booleanData;
	}

	public Resource withBooleanData(boolean booleanData) {
		this.booleanData = booleanData;
		return this;
	}
	
	public static List<Resource> getResources() {
		List<Resource> resources = new ArrayList<Resource>();
		Random random = new Random();
		for (int i = 0; i < random.nextInt(25); i++) {
			resources.add(
					new Resource()
						.withId(UUID.randomUUID().toString())
						.withData(randomString())
						.withValue(new Random().nextLong())
						.withCreated(new Date())
						.withBooleanData(new Random().nextBoolean())
					);
		}
		return resources;
	}
	
	public static Resource getResource() {
		return new Resource()
			.withId(UUID.randomUUID().toString())
			.withData(randomString())
			.withValue(new Random().nextLong())
			.withCreated(new Date())
			.withBooleanData(new Random().nextBoolean());
	}
	
	public static Resource getResource(String id) {
		return new Resource()
			.withId(id)
			.withData(randomString())
			.withValue(new Random().nextLong())
			.withCreated(new Date())
			.withBooleanData(new Random().nextBoolean());
	}
	
	private static String randomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
}

