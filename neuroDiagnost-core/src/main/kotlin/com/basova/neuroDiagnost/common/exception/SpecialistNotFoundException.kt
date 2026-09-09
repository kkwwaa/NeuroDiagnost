package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.specialistNotFound

class SpecialistNotFoundException(
    specialistId: Long
) : RuntimeException(specialistNotFound(specialistId))