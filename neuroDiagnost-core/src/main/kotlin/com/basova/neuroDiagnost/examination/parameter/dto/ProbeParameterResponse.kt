package com.basova.neuroDiagnost.examination.parameter.dto

import com.basova.neuroDiagnost.examination.parameter.enum.ParameterDirection
import com.basova.neuroDiagnost.examination.parameter.enum.ParameterValueType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Параметр для заполнения пробы")
data class ProbeParameterResponse(
    @Schema(
        description = "ID параметра",
        example = "1",
        required = true
    )
    val parameterId: Long,

    @Schema(
        description = "Название параметра",
        example = "Количество ошибок",
        required = true
    )
    val name: String,

    @Schema(
        description = "Тип значения параметра",
        example = "NUMBER",
        required = true
    )
    val valueType: ParameterValueType,

    @Schema(
        description = "Направление интерпретации значения",
        example = "DIRECT",
        required = true
    )
    val direction: ParameterDirection,

    @Schema(
        description = "Минимально допустимое числовое значение",
        example = "0",
    )
    val minValue: Double?,

    @Schema(
        description = "Максимально допустимое числовое значение",
        example = "10"
    )
    val maxValue: Double?,

    @Schema(
        description = "Вес параметра",
        example = "1.0",
        required = true
    )
    val weight: Double,

    @Schema(
        description = "Порядок параметра в пробе",
        example = "1",
        required = true
    )
    val sortOrder: Int,

    @Schema(
        description = "Допустимые варианты значения для параметра типа OPTION"
    )
    val options: List<ParameterOptionResponse>
)