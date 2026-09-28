package com.basova.neuroDiagnost.examination.session.controller

import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionProbeResponse
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import com.basova.neuroDiagnost.examination.session.service.SessionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Сессия найден")
    override fun findById(
        @Parameter(description = "ID сессии", example = "1")
        @PathVariable id: Long
    ): ResponseEntity<SessionResponse> {
        return ResponseEntity.ok(sessionService.findById(id))
    }

    @Operation(
        summary = "Получить пробы сессии",
        description = "Возвращает список проб сессии в порядке их выполнения"
    )
    @GetMapping("/{sessionId}/probes")
    @ApiResponse(responseCode = "200", description = "Пробы сессии найдены")
    override fun findProbes(
        @Parameter(description = "ID сессии", example = "1")
        @PathVariable sessionId: Long
    ): ResponseEntity<List<SessionProbeResponse>> {
        val probes = sessionService.findProbes(sessionId)

        return ResponseEntity.ok(probes)
    }


}