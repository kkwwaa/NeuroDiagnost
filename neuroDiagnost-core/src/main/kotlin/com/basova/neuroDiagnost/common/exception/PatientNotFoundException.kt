package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.patientNotFound

class PatientNotFoundException(
    patientId: Long
) : RuntimeException(patientNotFound(patientId))