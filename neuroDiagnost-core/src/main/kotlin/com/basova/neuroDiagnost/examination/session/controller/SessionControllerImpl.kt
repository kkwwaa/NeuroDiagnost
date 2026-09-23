package com.basova.neuroDiagnost.examination.session.controller

import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.patient.dto.CreatePatientRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import com.basova.neuroDiagnost.examination.session.service.SessionService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sessions")
@Tag(name = "Sessions")
class SessionControllerImpl (
    private val sessionService: SessionService
) : SessionController {

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Сессия создана")
    override fun create(
        @Valid @RequestBody request: CreateSessionRequest
    ): ResponseEntity<SessionResponse> {
        val session = sessionService.create(request)
        return ResponseEntity.ok(session)
    }
}