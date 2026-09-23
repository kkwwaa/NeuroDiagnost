package com.basova.neuroDiagnost.examination.session.mapper

import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import com.basova.neuroDiagnost.examination.session.entity.Session
import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.entity.Patient

object SessionMapper {

    fun toResponse(session: Session): SessionResponse {
        return SessionResponse(
            id = requireNotNull(session.id),
            patientId = session.patient.id!!,
            protocolId = session.protocol.id!!,
            status = requireNotNull(session.status),
            examinationDate = session.examinationDate
        )
    }
}
