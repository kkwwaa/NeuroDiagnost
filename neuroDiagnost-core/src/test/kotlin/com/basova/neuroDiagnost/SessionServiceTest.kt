package com.basova.neuroDiagnost

import com.basova.neuroDiagnost.common.exception.ProbeNotInSessionException
import com.basova.neuroDiagnost.common.exception.SessionNotFoundException
import com.basova.neuroDiagnost.examination.parameter.entity.Parameter
import com.basova.neuroDiagnost.examination.parameter.entity.ParameterOption
import com.basova.neuroDiagnost.examination.parameter.entity.ProbeParameter
import com.basova.neuroDiagnost.examination.parameter.enum.ParameterDirection
import com.basova.neuroDiagnost.examination.parameter.enum.ParameterValueType
import com.basova.neuroDiagnost.examination.parameter.repository.ParameterOptionRepository
import com.basova.neuroDiagnost.examination.parameter.repository.ProbeParameterRepository
import com.basova.neuroDiagnost.examination.probe.entity.Probe
import com.basova.neuroDiagnost.examination.protocol.repository.ProtocolProbeRepository
import com.basova.neuroDiagnost.examination.protocol.repository.ProtocolRepository
import com.basova.neuroDiagnost.examination.session.entity.Session
import com.basova.neuroDiagnost.examination.session.repository.SessionProbeRepository
import com.basova.neuroDiagnost.examination.session.repository.SessionRepository
import com.basova.neuroDiagnost.examination.session.service.SessionServiceImpl
import com.basova.neuroDiagnost.patient.repository.PatientRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Optional

class SessionServiceImplTest {

    private val sessionRepository = mockk<SessionRepository>()
    private val patientRepository = mockk<PatientRepository>()
    private val protocolRepository = mockk<ProtocolRepository>()
    private val protocolProbeRepository = mockk<ProtocolProbeRepository>()
    private val sessionProbeRepository = mockk<SessionProbeRepository>()
    private val probeParameterRepository = mockk<ProbeParameterRepository>()
    private val parameterOptionRepository = mockk<ParameterOptionRepository>()

    private lateinit var sessionService: SessionServiceImpl

    @BeforeEach
    fun setUp() {
        sessionService = SessionServiceImpl(
            sessionRepository = sessionRepository,
            patientRepository = patientRepository,
            protocolRepository = protocolRepository,
            protocolProbeRepository = protocolProbeRepository,
            sessionProbeRepository = sessionProbeRepository,
            probeParameterRepository = probeParameterRepository,
            parameterOptionRepository = parameterOptionRepository
        )
    }

    @Test
    fun `Возвращает параметры пробы с опциями`() {
        val sessionId = 1L
        val probeId = 201L

        val session = mockk<Session>()
        val probe = mockk<Probe>()

        val numberParameter = Parameter(
            id = 301,
            name = "Объём воспроизведения",
            valueType = ParameterValueType.NUMBER,
            direction = ParameterDirection.DIRECT,
            minValue = 0.0,
            maxValue = 5.0,
            weight = 1.0
        )

        val optionParameter = Parameter(
            id = 312,
            name = "Степень самостоятельности",
            valueType = ParameterValueType.OPTION,
            direction = ParameterDirection.DIRECT,
            weight = 0.7
        )

        val probeParameters = listOf(
            ProbeParameter(
                id = 601,
                probe = probe,
                parameter = numberParameter,
                sortOrder = 1
            ),
            ProbeParameter(
                id = 602,
                probe = probe,
                parameter = optionParameter,
                sortOrder = 2
            )
        )

        val options = listOf(
            ParameterOption(
                id = 405,
                parameter = optionParameter,
                value = "Самостоятельно",
                score = 1.0,
                sortOrder = 1
            ),
            ParameterOption(
                id = 406,
                parameter = optionParameter,
                value = "После подсказки",
                score = 0.5,
                sortOrder = 2
            )
        )

        every {
            sessionRepository.findById(sessionId)
        } returns Optional.of(session)

        every {
            sessionProbeRepository.existsBySessionIdAndProbeId(sessionId, probeId)
        } returns true

        every {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(probeId)
        } returns probeParameters

        every {
            parameterOptionRepository
                .findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(
                    listOf(301L, 312L)
                )
        } returns options

        val result = sessionService.findProbeParameters(sessionId, probeId)

        assertEquals(2, result.size)

        assertEquals(301L, result[0].parameterId)
        assertEquals(1, result[0].sortOrder)
        assertEquals(emptyList<Any>(), result[0].options)

        assertEquals(312L, result[1].parameterId)
        assertEquals(2, result[1].sortOrder)

        assertEquals(2, result[1].options.size)
        assertEquals(405L, result[1].options[0].optionId)
        assertEquals(1, result[1].options[0].sortOrder)
        assertEquals(406L, result[1].options[1].optionId)
        assertEquals(2, result[1].options[1].sortOrder)
    }

    @Test
    fun `Выбрасывает исключение когда сессии не существует`() {
        val sessionId = 999L
        val probeId = 201L

        every {
            sessionRepository.findById(sessionId)
        } returns Optional.empty()

        assertThrows(SessionNotFoundException::class.java) {
            sessionService.findProbeParameters(sessionId, probeId)
        }

        verify(exactly = 0) {
            sessionProbeRepository.existsBySessionIdAndProbeId(any(), any())
        }

        verify(exactly = 0) {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(any())
        }
    }

    @Test
    fun `Выбрасывает исключение когда проба не входит в сессию`() {
        val sessionId = 1L
        val probeId = 209L

        val session = mockk<Session>()

        every {
            sessionRepository.findById(sessionId)
        } returns Optional.of(session)

        every {
            sessionProbeRepository.existsBySessionIdAndProbeId(sessionId, probeId)
        } returns false

        assertThrows(ProbeNotInSessionException::class.java) {
            sessionService.findProbeParameters(sessionId, probeId)
        }

        verify(exactly = 0) {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(any())
        }
    }

    @Test
    fun `Возвращает пустой список когда у пробы нет параметров`() {
        val sessionId = 1L
        val probeId = 201L

        val session = mockk<Session>()

        every {
            sessionRepository.findById(sessionId)
        } returns Optional.of(session)

        every {
            sessionProbeRepository.existsBySessionIdAndProbeId(sessionId, probeId)
        } returns true

        every {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(probeId)
        } returns emptyList()

        val result = sessionService.findProbeParameters(sessionId, probeId)

        assertEquals(emptyList<Any>(), result)

        verify(exactly = 0) {
            parameterOptionRepository
                .findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(any())
        }
    }
}