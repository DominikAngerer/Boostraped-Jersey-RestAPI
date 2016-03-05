package com.dominikangerer.gson.provider.v1.util;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.Rule;

import com.google.gson.Gson;

import net.jadler.Jadler;
import net.jadler.junit.rule.JadlerRule;

public class GsonJerseyProviderIT {
  @Rule
  public JadlerRule jadlerRule = new JadlerRule();

  @Test
  public void testSerializeSimple() {
    Client client = ClientBuilder.newClient().register(GsonJerseyProvider.class);

    Jadler.onRequest()
      .havingMethodEqualTo("POST")
      .havingPathEqualTo("/serSimple")
      .havingBodyEqualTo("{\"i\":1}")
      .havingHeaderEqualTo("Content-type", "application/json")
      .respond()
      .withStatus(200);

    Foo foo = new Foo(1);
    Response response = client.target("http://localhost:" + Jadler.port() + "/serSimple").request().post(Entity.json(foo));
    assertEquals(response.getStatus(), 200);
  }

  @Test
  public void testSerializeList() {
    Client client = ClientBuilder.newClient().register(GsonJerseyProvider.class);

    Jadler.onRequest()
      .havingMethodEqualTo("POST")
      .havingPathEqualTo("/serList")
      .havingBodyEqualTo("[{\"i\":1}]")
      .havingHeaderEqualTo("Content-type", "application/json")
      .respond()
      .withStatus(200);

    List<Foo> list = Collections.singletonList(new Foo(1));
    Response response = client.target("http://localhost:" + Jadler.port() + "/serList").request().post(Entity.json(list));
    assertEquals(response.getStatus(), 200);
  }

  @Test
  public void testDeserializeSimple() {
    Client client = ClientBuilder.newClient().register(GsonJerseyProvider.class);

    Jadler.onRequest()
      .havingMethodEqualTo("GET")
      .havingPathEqualTo("/deserSimple")
      .respond()
      .withStatus(200)
      .withHeader("Content-type", "application/json")
      .withBody("{\"i\":2}");

    Response response = client.target("http://localhost:" + Jadler.port() + "/deserSimple").request().get();
    assertEquals(response.getStatus(), 200);
    assertEquals(response.readEntity(Foo.class).i, 2);
  }

  @Test
  public void testDeserializeList() {
    Client client = ClientBuilder.newClient().register(GsonJerseyProvider.class);

    Jadler.onRequest()
      .havingMethodEqualTo("GET")
      .havingPathEqualTo("/deserList")
      .respond()
      .withStatus(200)
      .withHeader("Content-type", "application/json")
      .withBody("[{\"i\":2}]");

    Response response = client.target("http://localhost:" + Jadler.port() + "/deserList").request().get();
    assertEquals(response.getStatus(), 200);
    List<Foo> list = response.readEntity(new GenericType<List<Foo>>() {});
    assertEquals(list.size(), 1);
    assertEquals(list.get(0).i, 2);
  }

  public static class Foo {
    public int i;
    public Foo(int i) {
      this.i = i;
    }
  }
}
