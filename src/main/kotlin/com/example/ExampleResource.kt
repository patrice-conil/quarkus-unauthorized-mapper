package com.example

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/")
class ExampleResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello from RESTEasy Reactive"

    @GET
    @Path("/helloKo")
    @Produces(MediaType.TEXT_PLAIN)
    fun helloKo() = "Should never succeed"
}