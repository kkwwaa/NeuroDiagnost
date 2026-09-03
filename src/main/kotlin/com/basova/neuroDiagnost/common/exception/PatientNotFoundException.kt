package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.Errors

class PatientNotFoundException(
    patientId: Long
) : RuntimeException(Errors.patientNotFound(patientId))