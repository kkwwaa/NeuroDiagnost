package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.sessionNotFound

class SessionNotFoundException(
    sessionId: Long
) : RuntimeException(sessionNotFound(sessionId))