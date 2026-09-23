package com.basova.neuroDiagnost.examination.session.service

import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import org.springframework.http.ResponseEntity

interface SessionService {
    fun create( request: CreateSessionRequest) : SessionResponse

    fun findById(sessionId: Long): SessionResponse
}