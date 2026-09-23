package com.basova.neuroDiagnost.examination.session.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Positive


@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Данные для создания сессии")
data class CreateSessionRequest (
    @field:Positive
    @Schema(
        description = "ID пациента, с которым проводят сессию",
        example = "1",
        required = true
    )
    val patientId: Long,

    @field:Positive
    @Schema(
        description = "ID протокола, по которому проводят сессию",
        example = "1",
        required = true
    )
    val protocolId: Long
)