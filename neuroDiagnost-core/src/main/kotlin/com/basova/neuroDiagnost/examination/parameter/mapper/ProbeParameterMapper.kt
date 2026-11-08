package com.basova.neuroDiagnost.examination.parameter.mapper

import com.basova.neuroDiagnost.examination.parameter.dto.ParameterOptionResponse
import com.basova.neuroDiagnost.examination.parameter.dto.ProbeParameterResponse
import com.basova.neuroDiagnost.examination.parameter.entity.ParameterOption
import com.basova.neuroDiagnost.examination.parameter.entity.ProbeParameter

fun toResponse(
    probeParameter: ProbeParameter,
    options: List<ParameterOption>
): ProbeParameterResponse {
    val parameter = probeParameter.parameter

    return ProbeParameterResponse(
        parameterId = requireNotNull(parameter.id),
        name = parameter.name,
        valueType = parameter.valueType,
        direction = parameter.direction,
        minValue = parameter.minValue,
        maxValue = parameter.maxValue,
        weight = parameter.weight,
        sortOrder = probeParameter.sortOrder,
        options = options.map {
            toResponse(it)
        }
    )
}

fun toResponse(option: ParameterOption): ParameterOptionResponse {
    return ParameterOptionResponse(
        optionId = requireNotNull(option.id),
        value = option.value,
        sortOrder = option.sortOrder
    )
}