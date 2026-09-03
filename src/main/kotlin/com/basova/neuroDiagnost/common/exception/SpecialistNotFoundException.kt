package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.Errors

class SpecialistNotFoundException(
    specialistId: Long
) : RuntimeException(Errors.specialistNotFound(specialistId))