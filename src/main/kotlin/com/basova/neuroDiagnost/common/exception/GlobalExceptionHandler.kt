package com.basova.neuroDiagnost.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(PatientNotFoundException::class)
    fun handlePatientNotFound(
        exception: PatientNotFoundException
    ): ProblemDetail {

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.message ?: "Patient not found"
            )
            .apply {
                title = "Patient not found"
            }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        exception: MethodArgumentNotValidException
    ): ProblemDetail {

        val errors = exception.bindingResult
            .fieldErrors
            .associate {
                it.field to (it.defaultMessage ?: "Invalid value")
            }

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Request validation failed"
            )
            .apply {
                title = "Validation error"
                setProperty("errors", errors)
            }
    }
}

