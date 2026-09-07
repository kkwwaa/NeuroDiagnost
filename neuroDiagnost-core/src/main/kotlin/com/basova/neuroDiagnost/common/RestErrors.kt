package com.basova.neuroDiagnost.common


const val ERRORS = "errors"
const val PATIENT_NOT_FOUND = "Patient not found"
const val SPECIALIST_NOT_FOUND = "Specialist not found"
const val VALIDATION_ERROR = "Validation error"
const val REQUEST_VALIDATION_FAILED = "Request validation failed"
const val INVALID_VALUE = "Invalid value"
fun patientNotFound(id: Long) = "Patient with id=$id not found"
fun specialistNotFound(id: Long) = "Specialist with id=$id not found"
