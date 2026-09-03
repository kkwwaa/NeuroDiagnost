package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.Errors
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
                exception.message ?: Errors.PATIENT_NOT_FOUND
            )
            .apply {
                title = Errors.PATIENT_NOT_FOUND
            }
    }

    @ExceptionHandler(SpecialistNotFoundException::class)
    fun handleSpecialistNotFound(
        exception: SpecialistNotFoundException
    ): ProblemDetail {

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.message ?: Errors.SPECIALIST_NOT_FOUND
            )
            .apply {
                title = Errors.SPECIALIST_NOT_FOUND
            }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        exception: MethodArgumentNotValidException
    ): ProblemDetail {

        val errors = exception.bindingResult
            .fieldErrors
            .associate {
                it.field to (it.defaultMessage ?: Errors.INVALID_VALUE)
            }

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                Errors.REQUEST_VALIDATION_FAILED
            )
            .apply {
                title = Errors.VALIDATION_ERROR
                setProperty(Errors.ERRORS, errors)
            }
    }
}

