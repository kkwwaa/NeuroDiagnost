package com.basova.neuroDiagnost.examination.session.service

import com.basova.neuroDiagnost.examination.protocol.repository.ProtocolProbeRepository
import com.basova.neuroDiagnost.examination.protocol.repository.ProtocolRepository
import com.basova.neuroDiagnost.examination.session.dto.CreateSessionRequest
import com.basova.neuroDiagnost.examination.session.dto.SessionResponse
import com.basova.neuroDiagnost.examination.session.entity.Session
import com.basova.neuroDiagnost.examination.session.entity.SessionProbe
import com.basova.neuroDiagnost.examination.session.dto.SessionProbeResponse
import com.basova.neuroDiagnost.common.exception.PatientNotFoundException
import com.basova.neuroDiagnost.common.exception.ProtocolNotFoundException
import com.basova.neuroDiagnost.common.exception.SessionNotFoundException
import com.basova.neuroDiagnost.examination.session.enum.SessionProbeStatus
import com.basova.neuroDiagnost.examination.session.enum.SessionStatus
import com.basova.neuroDiagnost.examination.session.mapper.SessionMapper
import com.basova.neuroDiagnost.examination.session.mapper.SessionProbeMapper
import com.basova.neuroDiagnost.examination.session.repository.SessionProbeRepository
import com.basova.neuroDiagnost.examination.session.repository.SessionRepository
import com.basova.neuroDiagnost.patient.repository.PatientRepository
import org.springframework.transaction.annotation.Transactional
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

    @Transactional(readOnly = true)
    override fun findById(sessionId: Long): SessionResponse {
        val session = findSessionById(sessionId)

        return SessionMapper.toResponse(session)
    }

    @Transactional(readOnly = true)
    override fun findProbes(sessionId: Long): List<SessionProbeResponse> {
        val session = findSessionById(sessionId)

        val sessionProbesByProbeId = sessionProbeRepository
            .findAllBySessionId(sessionId)
            .associateBy { sessionProbe ->
                requireNotNull(sessionProbe.probe.id)
            }

        val protocolId = requireNotNull(session.protocol.id)

        return protocolProbeRepository
            .findAllByProtocolIdOrderBySortOrder(protocolId)
            .map { protocolProbe ->
                val probeId = requireNotNull(protocolProbe.probe.id)

                val sessionProbe = requireNotNull(sessionProbesByProbeId[probeId])

                SessionProbeMapper.toResponse(
                    sessionProbe = sessionProbe,
                    sortOrder = protocolProbe.sortOrder
                )
            }
    }

    private fun findSessionById(id: Long): Session {
        return sessionRepository.findById(id)
            .orElseThrow {
                SessionNotFoundException(id)
            }
    }
}