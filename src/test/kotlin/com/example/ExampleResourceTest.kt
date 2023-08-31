package com.example

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test


class ExampleResourceTest {

    @Test
    fun `should return 200 when everything is ok`() {
        given()
            .auth().preemptive().basic("user", "password")
            .`when`()
            .log().all()
            .get("/hello")
            .then()
            .log().all()
            .statusCode(200)
            .body("message", `is`("Hello from RESTEasy Reactive"))
    }

    @Test
    fun `should return 401 when authentication is incorrect`() {
        given()
            .auth().preemptive().basic("user", "not those expected")
            .`when`()
            .get("/hello")
            .then()
            .log().all()
            .statusCode(401)
            .body("code", CoreMatchers.containsString("UNAUTHORIZED"))
    }

    @Test
    fun `should return 401 when authentication is missing`() {
        given()
            .`when`()
            .get("/hello")
            .then()
            .log().all()
            .statusCode(401)
            .body("code", CoreMatchers.containsString("UNAUTHORIZED"))
    }

    @Test
    fun `should return 403`() {
        given()
            .auth().basic("user", "password")
            .`when`()
            .get("/helloKo")
            .then()
            .log().all()
            .contentType(ContentType.JSON)
            .statusCode(403)
            .body("code", CoreMatchers.containsString("FORBIDDEN"))
    }

    @Test
    fun `should return 401 on non annotated`() {
        given()
            .`when`()
            .get("/helloNotAnnotated")
            .then()
            .log().all()
            .statusCode(401)
            .body("code", CoreMatchers.containsString("UNAUTHORIZED"))
    }

}