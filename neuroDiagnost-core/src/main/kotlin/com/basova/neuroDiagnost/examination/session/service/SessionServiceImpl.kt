package com.basova.neuroDiagnost.examination.session.service

import com.basova.neuroDiagnost.examination.protocol.repository.ProtocolProbeRepository
import com.basova.neuroDiagnost.examination.protocol.repository.ProtocolRepository
import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import com.basova.neuroDiagnost.examination.session.entity.Session
import com.basova.neuroDiagnost.examination.session.entity.SessionProbe
import com.basova.neuroDiagnost.common.exception.PatientNotFoundException
import com.basova.neuroDiagnost.common.exception.ProtocolNotFoundException
import com.basova.neuroDiagnost.examination.session.enum.SessionProbeStatus
import com.basova.neuroDiagnost.examination.session.enum.SessionStatus
import com.basova.neuroDiagnost.examination.session.mapper.SessionMapper
import com.basova.neuroDiagnost.examination.session.repository.SessionProbeRepository
import com.basova.neuroDiagnost.examination.session.repository.SessionRepository
import com.basova.neuroDiagnost.patient.repository.PatientRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SessionServiceImpl (
    val sessionRepository: SessionRepository,
    val patientRepository: PatientRepository,
    val protocolRepository: ProtocolRepository,
    val protocolProbeRepository: ProtocolProbeRepository,
    val sessionProbeRepository: SessionProbeRepository
) : SessionService {

    @Transactional
    override fun create(request: CreateSessionRequest): SessionResponse {
        val patient = patientRepository.findById(request.patientId)
            .orElseThrow {
                PatientNotFoundException(request.patientId)
            }

        val protocol = protocolRepository.findById(request.protocolId)
            .orElseThrow {
                ProtocolNotFoundException(request.protocolId)
            }

        val session = Session(
            patient = patient,
            protocol = protocol,
            status = SessionStatus.IN_PROGRESS
        )

        val savedSession = sessionRepository.save(session)

        val sessionProbes = protocolProbeRepository
            .findAllByProtocolIdOrderBySortOrder(request.protocolId)
            .map { protocolProbe ->
                SessionProbe(
                    session = savedSession,
                    probe = protocolProbe.probe,
                    status = SessionProbeStatus.NOT_STARTED
                )
            }

        sessionProbeRepository.saveAll(sessionProbes)

        return SessionMapper.toResponse(savedSession)
    }
}