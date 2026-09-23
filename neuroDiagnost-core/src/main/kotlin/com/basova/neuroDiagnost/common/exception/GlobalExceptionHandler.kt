package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.*
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
                exception.message ?: PATIENT_NOT_FOUND
            )
            .apply {
                title = PATIENT_NOT_FOUND
            }
    }

    @ExceptionHandler(SpecialistNotFoundException::class)
    fun handleSpecialistNotFound(
        exception: SpecialistNotFoundException
    ): ProblemDetail {

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.message ?: SPECIALIST_NOT_FOUND
            )
            .apply {
                title = SPECIALIST_NOT_FOUND
            }
    }

    @ExceptionHandler(ProtocolNotFoundException::class)
    fun handleProtocolNotFound(
        exception: ProtocolNotFoundException
    ): ProblemDetail {

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.message ?: PROTOCOL_NOT_FOUND
            )
            .apply {
                title = PROTOCOL_NOT_FOUND
            }
    }

    @ExceptionHandler(SessionNotFoundException::class)
    fun handleSessionNotFound(
        exception: SessionNotFoundException
    ): ProblemDetail {

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.message ?: SESSION_NOT_FOUND
            )
            .apply {
                title = SESSION_NOT_FOUND
            }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        exception: MethodArgumentNotValidException
    ): ProblemDetail {

        val errors = exception.bindingResult
            .fieldErrors
            .associate {
                it.field to (it.defaultMessage ?: INVALID_VALUE)
            }

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                REQUEST_VALIDATION_FAILED
            )
            .apply {
                title = VALIDATION_ERROR
                setProperty(ERRORS, errors)
            }
    }
}

