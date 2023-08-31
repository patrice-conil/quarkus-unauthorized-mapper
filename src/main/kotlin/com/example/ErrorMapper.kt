package com.example

import io.quarkus.security.AuthenticationFailedException
import io.quarkus.security.ForbiddenException
import io.quarkus.security.UnauthorizedException
import jakarta.annotation.Priority
import jakarta.ws.rs.ext.Provider
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper

@Provider
@Priority(1)
class ErrorMapper {

    @ServerExceptionMapper(UnauthorizedException::class)
    fun unauthorized(e: UnauthorizedException) = RestResponse.status(
        RestResponse.Status.UNAUTHORIZED,
        ErrorInfo(401, "UNAUTHORIZED", e.message ?: "My custom message for Unauthorized")
    )

    @ServerExceptionMapper(AuthenticationFailedException::class)
    fun authenticationFailed(e: AuthenticationFailedException) = RestResponse.status(
        RestResponse.Status.UNAUTHORIZED,
        ErrorInfo(401, "UNAUTHORIZED", e.message ?: "My custom message for AuthenticationFailed")
    )

    @ServerExceptionMapper(ForbiddenException::class)
    fun forbidden(e: ForbiddenException) = RestResponse.status(
        RestResponse.Status.FORBIDDEN,
        ErrorInfo(RestResponse.Status.FORBIDDEN.statusCode, "FORBIDDEN", e.message)
    )

}