package com.basova.neuroDiagnost.examination.session.dto

import com.basova.neuroDiagnost.examination.session.enum.SessionStatus
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Сессия")
data class SessionResponse (
    @Schema(description = "ID сессии", example = "1", required = true)
    val id: Long,

    @Schema(description = "ID пациента, с которым проводят сессию", example = "1", required = true)
    val patientId: Long,

    @Schema(description = "ID протокола, по которому проводят сессию", example = "1", required = true)
    val protocolId: Long,

    @Schema(description = "Статус сессии", example = "IN_PROGRESS", required = false)
    val status: SessionStatus,

    @Schema(description = "Дата начала сессии", example = "2018-05-12", required = true)
    val examinationDate: LocalDate
)