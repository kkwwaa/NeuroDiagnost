package com.basova.neuroDiagnost.examination.session.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull


@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Данные для создания сессии")
data class CreateSessionRequest (
    @field:NotNull // todo: ai предлагает заменить все нотНал при id на Positive аннотацию
    @Schema(
        description = "ID пациента, с которым проводят сессию",
        example = "1",
        required = true
    )
    val patientId: Long,

    @field:NotNull
    @Schema(
        description = "ID протокола, по которому проводят сессию",
        example = "1",
        required = true
    )
    val protocolId: Long
)