package com.basova.neuroDiagnost.examination.session.service

import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionProbeResponse
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse

interface SessionService {
    fun create( request: CreateSessionRequest) : SessionResponse

    fun findById(sessionId: Long): SessionResponse

    fun findProbes(sessionId: Long): List<SessionProbeResponse>
}