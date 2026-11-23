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
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.Optional
import kotlin.random.Random

class SessionServiceImplTest {

    private val sessionRepository = mockk<SessionRepository>()
    private val patientRepository = mockk<PatientRepository>()
    private val protocolRepository = mockk<ProtocolRepository>()
    private val protocolProbeRepository = mockk<ProtocolProbeRepository>()
    private val sessionProbeRepository = mockk<SessionProbeRepository>()
    private val probeParameterRepository = mockk<ProbeParameterRepository>()
    private val parameterOptionRepository = mockk<ParameterOptionRepository>()

    private val sessionService = SessionServiceImpl(
        sessionRepository = sessionRepository,
        patientRepository = patientRepository,
        protocolRepository = protocolRepository,
        protocolProbeRepository = protocolProbeRepository,
        sessionProbeRepository = sessionProbeRepository,
        probeParameterRepository = probeParameterRepository,
        parameterOptionRepository = parameterOptionRepository
    )

    @Test
    fun `Возвращает параметры пробы с опциями`() {
        val sessionId = randomId()
        val probeId = randomId()

        val numberParameterId = randomId()
        val optionParameterId = randomId()

        val firstOptionId = randomId()
        val secondOptionId = randomId()

        val session = mockk<Session>()

        val numberParameter = createParameter(
            parameterId = numberParameterId,
            type = ParameterValueType.NUMBER
        )

        val optionParameter = createParameter(
            parameterId = optionParameterId,
            type = ParameterValueType.OPTION
        )

        val probeParameters = listOf(
            createProbeParameter(
                parameter = numberParameter,
                sortOrder = FIRST_SORT_ORDER
            ),
            createProbeParameter(
                parameter = optionParameter,
                sortOrder = SECOND_SORT_ORDER
            )
        )

        val options = listOf(
            createParameterOption(
                optionId = firstOptionId,
                parameter = optionParameter,
                sortOrder = FIRST_SORT_ORDER
            ),
            createParameterOption(
                optionId = secondOptionId,
                parameter = optionParameter,
                sortOrder = SECOND_SORT_ORDER
            )
        )

        val parameterIds = listOf(
            numberParameterId,
            optionParameterId
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
                .findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(parameterIds)
        } returns options

        val result = sessionService.findProbeParameters(sessionId, probeId)

        assertEquals(probeParameters.size, result.size)

        assertEquals(numberParameterId, result[0].parameterId)
        assertEquals(FIRST_SORT_ORDER, result[0].sortOrder)
        assertTrue(result[0].options.isEmpty())

        assertEquals(optionParameterId, result[1].parameterId)
        assertEquals(SECOND_SORT_ORDER, result[1].sortOrder)

        assertEquals(options.size, result[1].options.size)

        assertEquals(firstOptionId, result[1].options[0].optionId)
        assertEquals(FIRST_SORT_ORDER, result[1].options[0].sortOrder)

        assertEquals(secondOptionId, result[1].options[1].optionId)
        assertEquals(SECOND_SORT_ORDER, result[1].options[1].sortOrder)

        verify(exactly = 1) {
            sessionRepository.findById(sessionId)
        }

        verify(exactly = 1) {
            sessionProbeRepository.existsBySessionIdAndProbeId(sessionId, probeId)
        }

        verify(exactly = 1) {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(probeId)
        }

        verify(exactly = 1) {
            parameterOptionRepository
                .findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(parameterIds)
        }
    }

    @Test
    fun `Выбрасывает исключение когда сессии не существует`() {
        val sessionId = randomId()
        val probeId = randomId()

        every {
            sessionRepository.findById(sessionId)
        } returns Optional.empty()

        assertThrows(SessionNotFoundException::class.java) {
            sessionService.findProbeParameters(sessionId, probeId)
        }

        verify(exactly = 1) {
            sessionRepository.findById(sessionId)
        }

        verify(exactly = 0) {
            sessionProbeRepository.existsBySessionIdAndProbeId(any(), any())
        }

        verify(exactly = 0) {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(any())
        }

        verify(exactly = 0) {
            parameterOptionRepository
                .findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(any())
        }
    }

    @Test
    fun `Выбрасывает исключение когда проба не входит в сессию`() {
        val sessionId = randomId()
        val probeId = randomId()

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

        verify(exactly = 1) {
            sessionRepository.findById(sessionId)
        }

        verify(exactly = 1) {
            sessionProbeRepository.existsBySessionIdAndProbeId(sessionId, probeId)
        }

        verify(exactly = 0) {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(any())
        }

        verify(exactly = 0) {
            parameterOptionRepository
                .findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(any())
        }
    }

    @Test
    fun `Возвращает пустой список когда у пробы нет параметров`() {
        val sessionId = randomId()
        val probeId = randomId()

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

        assertTrue(result.isEmpty())

        verify(exactly = 1) {
            sessionRepository.findById(sessionId)
        }

        verify(exactly = 1) {
            sessionProbeRepository.existsBySessionIdAndProbeId(sessionId, probeId)
        }

        verify(exactly = 1) {
            probeParameterRepository.findAllByProbeIdOrderBySortOrder(probeId)
        }

        verify(exactly = 0) {
            parameterOptionRepository
                .findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(any())
        }
    }

    private fun createParameter(
        parameterId: Long = randomId(),
        type: ParameterValueType = ParameterValueType.NUMBER
    ): Parameter {
        val parameter = mockk<Parameter>()

        val minValue = if (type == ParameterValueType.NUMBER) {
            DEFAULT_MIN_VALUE
        } else {
            null
        }

        val maxValue = if (type == ParameterValueType.NUMBER) {
            DEFAULT_MAX_VALUE
        } else {
            null
        }

        every { parameter.id } returns parameterId
        every { parameter.name } returns DEFAULT_PARAMETER_NAME
        every { parameter.valueType } returns type
        every { parameter.direction } returns ParameterDirection.DIRECT
        every { parameter.minValue } returns minValue
        every { parameter.maxValue } returns maxValue
        every { parameter.weight } returns DEFAULT_WEIGHT

        return parameter
    }

    private fun createProbeParameter(
        parameter: Parameter,
        sortOrder: Int
    ): ProbeParameter {
        val probeParameter = mockk<ProbeParameter>()

        every { probeParameter.parameter } returns parameter
        every { probeParameter.sortOrder } returns sortOrder

        return probeParameter
    }

    private fun createParameterOption(
        optionId: Long = randomId(),
        parameter: Parameter,
        sortOrder: Int
    ): ParameterOption {
        val option = mockk<ParameterOption>()

        every { option.id } returns optionId
        every { option.parameter } returns parameter
        every { option.value } returns DEFAULT_OPTION_VALUE
        every { option.sortOrder } returns sortOrder

        return option
    }

    private fun randomId(): Long {
        return Random.nextLong(1, Long.MAX_VALUE)
    }

    private companion object {
        const val DEFAULT_PARAMETER_NAME = "Тестовый параметр"
        const val DEFAULT_OPTION_VALUE = "Тестовая опция"

        const val DEFAULT_MIN_VALUE = 0.0
        const val DEFAULT_MAX_VALUE = 100.0
        const val DEFAULT_WEIGHT = 1.0

        const val FIRST_SORT_ORDER = 1
        const val SECOND_SORT_ORDER = 2
    }
}