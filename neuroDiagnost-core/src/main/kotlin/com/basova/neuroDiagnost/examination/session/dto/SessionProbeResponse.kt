package com.basova.neuroDiagnost.examination.session.dto

import com.basova.neuroDiagnost.examination.session.enum.SessionProbeStatus
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Проба в рамках сессии обследования")
data class SessionProbeResponse(
    @Schema(description = "ID пробы", example = "1", required = true)
    val probeId: Long,

    @Schema(description = "Название пробы", example = "Запоминание 10 слов", required = true)
    val name: String,

    @Schema(description = "Инструкция к пробе")
    val instruction: String?,

    @Schema(description = "Порядок выполнения пробы", example = "1", required = true)
    val sortOrder: Long,

    @Schema(description = "Статус выполнения пробы", example = "NOT_STARTED", required = true)
    val status: SessionProbeStatus,

    @Schema(description = "Комментарий специалиста")
    val note: String?,

    @Schema(description = "Дата и время начала выполнения пробы")
    val startedAt: LocalDateTime?,

    @Schema(description = "Дата и время завершения выполнения пробы")
    val completedAt: LocalDateTime?
)