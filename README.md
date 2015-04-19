# GsonJerseyProviderExample

### GsonJerseyBodyParser
GsonJerseyProvider is an example on how to use the Google Gson Library as an Provider on Jersey JAX-RS applications.

The original Version of the GsonJerseyProvidor.java can be found here: [@eclipsesource]:

> Creating modern applications often involves consuming REST
> based web services. One of the more popular ways to publish 
> a REST service in Java is the JAX-RS (Jersey) specification.
> It allows you to very easily enhance your REST resources 
> with Java annotations.

by [Moritz Post]. 

I needed an working example, because I had some issues getting started only with his class and I couldn't just leave it dead on my disk.

#### The configuration part
First to get the class of [Moritz Post] running we need to do a little bit of configuration so Jersey knows to load the provider (`web.xml`):
```xml
<servlet>
	<servlet-name>Services</servlet-name>
	<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
	<init-param>
		<param-name>jersey.config.server.provider.packages</param-name>
		<param-value>
  			com.dominikangerer.gson.provider.v1.util,
  			com.dominikangerer.gson.provider.v1.controller
  		</param-value>
	</init-param>
	<init-param>
		<param-name>jersey.config.server.provider.scanning.recursive</param-name>
		<param-value>false</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
```
Now you should be able to run the example from [Moritz Post] but you will have to add the `GsonUtil` to get the version I provided in this repository as well - so let me explain it a little bit to you.

### GsonUtil

In this GsonUtil I added the DateFormat `yyyy-MM-dd'T'HH:mm:ss.SSSXX` as default DateFormat to Gson by adding new `registerTypeAdapter` for serializing and deserializing. I also added the possibility to load a preconfigurated instance of Gson to show only properties with `@Expose` Annotation.

### Container
Why? Javascipt Frameworks like Ember can be used with an RESTAdapter which provide such an pattern of responses - so why not return such an formated Json?

In this Repository you can find an simple Controller for `GET`ting an `List<Resource>` and simply `GET` an `Resource` by id. To Achieve this on an structured way I added an new `container` package which can be used for `resources` and also for an single `resource`. It also capsules the whole `Response.status(Response.Status.X).entity(Y).build();` stuff for you so you can simply use it like this in your Controller to return the action you performed with the right success status:

```java
return ResourceContainer.ok();
return ResourceContainer.ok(Resource);
return ResourceContainer.ok(Resource List);
return ResourceContainer.ok(Resource ArrayList);
return ResourceContainer.ok(Resource Collection);
return ResourceContainer.updated();
return ResourceContainer.updated(Resource);
return ResourceContainer.created(Resource);
return ResourceContainer.deleted();
```

### Version
1.0.1

### Installation

You need an Container like Tomcat and clone this repository:

```git
git clone git@github.com:DominikAngerer/java-GsonJerseyProvider.git
```

Import it into your favourite IDE (i'm using Eclipse Luna) and enjoy.

### Calls

~~Don't mind if you get an 404 on path: `/`.~~
Now it will return: `Okay, I hated the 404 Page here ;)`, because I really did.

- Get List of Resources
`http://localhost:8080/gson.provider/v1/resources`

- get Resource by Id
`http://localhost:8080/gson.provider/v1/resources/b1e581bf-8265-480c-a460-50c860df15be`

### Links

- Blog Entry [@Lanora]: http://lanora.io/2014/11/gson-jersey-provider/
- Eclipsesource by [Moritz Post]: http://eclipsesource.com/blogs/2012/11/02/integrating-gson-into-a-jax-rs-based-application/

[@eclipsesource]:http://eclipsesource.com/blogs/2012/11/02/integrating-gson-into-a-jax-rs-based-application/
[Moritz Post]:http://profiles.google.com/110380137820502036004
[@Lanora]:http://lanora.io/2014/11/gson-jersey-provider/
[Mike Penz]:https://github.com/mikepenz
[Thomas Pink]:https://github.com/thomaspink

### Stackoverflow

- Stackoverflow #1: http://stackoverflow.com/a/9516224/1581725
- Stackoverflow #2: http://stackoverflow.com/a/29551143/1581725
- Stackoverflow #3: http://stackoverflow.com/a/29731719/1581725
- Stackoverflow #4: http://stackoverflow.com/a/26829444/1581725
- Stackoverflow #5: http://stackoverflow.com/a/29551143/1581725

