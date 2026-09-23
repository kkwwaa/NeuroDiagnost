package com.basova.neuroDiagnost.examination.session.controller

import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import org.springframework.http.ResponseEntity

interface SessionController {
    fun create( request: CreateSessionRequest) : ResponseEntity<SessionResponse>

    fun findById(id: Long) : ResponseEntity<SessionResponse>

}