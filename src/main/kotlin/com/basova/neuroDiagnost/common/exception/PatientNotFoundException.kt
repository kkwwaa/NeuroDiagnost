package com.basova.neuroDiagnost.common.exception

class PatientNotFoundException(
    patientId: Long
) : RuntimeException("Patient with id=$patientId not found")