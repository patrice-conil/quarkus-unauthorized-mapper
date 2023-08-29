package com.example

import io.quarkus.security.ForbiddenException
import io.quarkus.security.UnauthorizedException
import jakarta.annotation.Priority
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper

class ErrorMapper {

    private fun mapExceptionIntoError(
        status: Response.Status,
        code: String,
        message: String?,
    ): RestResponse<ErrorInfo> {
        return RestResponse.status(
            status,
            ErrorInfo(status.statusCode, code, message)
        )
    }

    @ServerExceptionMapper
    @Priority(1)
    fun unauthorized(e: UnauthorizedException) =
        mapExceptionIntoError(Response.Status.UNAUTHORIZED, "UNAUTHORIZED", e.message)

    @ServerExceptionMapper
    @Priority(1)
    fun forbidden(e: ForbiddenException) =
        mapExceptionIntoError(Response.Status.FORBIDDEN, "FORBIDDEN", e.message)

}