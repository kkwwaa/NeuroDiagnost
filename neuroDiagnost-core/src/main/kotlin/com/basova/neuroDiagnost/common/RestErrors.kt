package com.basova.neuroDiagnost.common

const val ERRORS = "errors"
const val PATIENT_NOT_FOUND = "Patient not found"
const val PROTOCOL_NOT_FOUND = "Protocol not found"
const val SPECIALIST_NOT_FOUND = "Specialist not found"
const val SESSION_NOT_FOUND = "Session not found"
const val PROBE_NOT_IN_SESSION = "Probe not in session"

const val VALIDATION_ERROR = "Validation error"
const val REQUEST_VALIDATION_FAILED = "Request validation failed"
const val INVALID_VALUE = "Invalid value"
fun patientNotFound(id: Long) = "Patient with id=$id not found"
fun specialistNotFound(id: Long) = "Specialist with id=$id not found"
fun protocolNotFound(id: Long) = "Protocol with id=$id not found"
fun sessionNotFound(id: Long) = "Session with id=$id not found"
fun probeNotInSession(sessionId: Long, probeId: Long) = "Probe with id=$probeId not in session with id=$sessionId"

