package com.basova.neuroDiagnost.examination.session.mapper

import com.basova.neuroDiagnost.examination.session.dto.SessionProbeResponse
import com.basova.neuroDiagnost.examination.session.entity.SessionProbe

object SessionProbeMapper {

    fun toResponse(
        sessionProbe: SessionProbe,
        sortOrder: Long
    ): SessionProbeResponse {
        return SessionProbeResponse(
            probeId = requireNotNull(sessionProbe.probe.id),
            name = sessionProbe.probe.name,
            instruction = sessionProbe.probe.instruction,
            sortOrder = sortOrder,
            status = sessionProbe.status,
            note = sessionProbe.note,
            startedAt = sessionProbe.startedAt,
            completedAt = sessionProbe.completedAt
        )
    }
}