package com.basova.neuroDiagnost.examination.session.controller

import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionProbeResponse
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity

interface SessionController {
    fun create( request: CreateSessionRequest) : ResponseEntity<SessionResponse>

    fun findById(id: Long) : ResponseEntity<SessionResponse>

    @Operation(
        summary = "Получить пробы сессии",
        description = "Возвращает список проб сессии в порядке их выполнения"
    )
    fun findProbes(sessionId: Long) : ResponseEntity<List<SessionProbeResponse>>

}