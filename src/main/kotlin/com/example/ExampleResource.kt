package com.example

import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse

@Path("/")
class ExampleResource {

    data class Reply(val message: String)

    @GET
    @Path("/hello")
    @RolesAllowed("role1")
    fun hello(): RestResponse<Reply> = RestResponse.ok(Reply("Hello from RESTEasy Reactive"))

    @GET
    @Path("/helloKo")
    @RolesAllowed("admin")
    fun helloKo() = RestResponse.ok(Reply("Should never succeed"))


    @GET
    @Path("/helloNotAnnotated")
    @Produces(MediaType.APPLICATION_JSON)
    fun helloNotAnnotated() = RestResponse.ok(Reply("Hello from helloNotAnnotated"))
}