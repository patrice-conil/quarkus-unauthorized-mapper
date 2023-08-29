package com.example

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import io.quarkus.security.UnauthorizedException
import io.vertx.ext.web.Router;


@ApplicationScoped
class UnauthorizedExceptionExceptionHandler {
    fun init(@Observes router: Router) {
        router.route().failureHandler { event ->
            if (event.failure() is UnauthorizedException) {
                event.response().end("CUSTOMIZED_RESPONSE")
            } else {
                event.next()
            }
        }
    }
}