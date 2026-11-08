package com.basova.neuroDiagnost.common.exception

import com.basova.neuroDiagnost.common.probeNotInSession

class ProbeNotInSessionException(sessionId: Long, probeId: Long) : RuntimeException(probeNotInSession(sessionId, probeId))