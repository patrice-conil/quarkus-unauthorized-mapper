package com.example

import io.quarkus.security.UnauthorizedException
import jakarta.annotation.Priority
import jakarta.ws.rs.Priorities
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import java.util.logging.Logger

@Provider
@Priority(Priorities.AUTHORIZATION)
class UnauthorizedErrorMapper : ExceptionMapper<UnauthorizedException> {

    private val logger: Logger = Logger.getLogger(this.javaClass.simpleName)

    @Override
    override fun toResponse(exception: UnauthorizedException): Response {
        logger.severe(exception.message)

        val message = exception.message
        val code = STATUS.statusCode
        val error =  ErrorInfo(STATUS.statusCode, STATUS.name, message)

        return Response
            .status(code)
            .entity(error)
            .build()
    }

    companion object {
        private val STATUS = Response.Status.UNAUTHORIZED
    }
}
