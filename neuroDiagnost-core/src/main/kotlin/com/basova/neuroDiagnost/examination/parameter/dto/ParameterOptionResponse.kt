package com.basova.neuroDiagnost.examination.parameter.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Вариант значения параметра")
data class ParameterOptionResponse(
    @Schema(
        description = "ID варианта значения",
        example = "1",
        required = true
    )
    val optionId: Long,

    @Schema(
        description = "Значение варианта",
        example = "Не выполнил",
        required = true
    )
    val value: String,

    @Schema(
        description = "Порядок отображения варианта",
        example = "1",
        required = true
    )
    val sortOrder: Int
)