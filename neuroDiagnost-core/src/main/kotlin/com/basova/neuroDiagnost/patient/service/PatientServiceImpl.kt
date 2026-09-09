package com.basova.neuroDiagnost.patient.service

import com.basova.neuroDiagnost.auth.entity.User
import com.basova.neuroDiagnost.auth.repository.UserRepository
import com.basova.neuroDiagnost.common.exception.PatientNotFoundException
import com.basova.neuroDiagnost.common.exception.SpecialistNotFoundException
import com.basova.neuroDiagnost.patient.dto.CreatePatientRequest
import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.dto.UpdatePatientRequest
import com.basova.neuroDiagnost.patient.entity.Patient
import com.basova.neuroDiagnost.patient.mapper.PatientMapper
import com.basova.neuroDiagnost.patient.repository.PatientRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class PatientServiceImpl(
    val patientRepository: PatientRepository,
    val userRepository: UserRepository
): PatientService {
    @Transactional
    override fun create(request: CreatePatientRequest): PatientResponse {
        val specialist = findSpecialistById(request.specialistId)

        val patient = Patient(
            specialist = specialist,
            fullName = requireNotNull(request.fullName).trim(),
            birthDate = requireNotNull(request.birthDate),
            sex = request.sex,
            parentName = request.parentName?.trim(),
            phone = request.phone?.trim(),
            anamnesis = request.anamnesis,
        )

        val savedPatient = patientRepository.save(patient)

        return PatientMapper.toResponse(savedPatient)
    }

    @Transactional(readOnly = true)
    override fun findById(patientId: Long): PatientResponse {
        val patient = findPatientById(patientId)

        return PatientMapper.toResponse(patient)
    }

    @Transactional(readOnly = true)
    override fun findAllBySpecialistId(specialistId: Long): List<PatientResponse> {
        val patients = patientRepository.findAllBySpecialistId(specialistId).map {
            PatientMapper.toResponse(it)
        }

        return patients
    }

    @Transactional
    override fun update(id: Long, request: UpdatePatientRequest): PatientResponse {
        val patient = findPatientById(id)

        patient.fullName = request.fullName.trim()
        patient.birthDate = request.birthDate
        patient.sex = request.sex
        patient.parentName = request.parentName?.trim()
        patient.phone = request.phone?.trim()
        patient.anamnesis = request.anamnesis?.trim()

        return PatientMapper.toResponse(patient)
    }

    @Transactional
    override fun delete(id: Long) {
        val patient = findPatientById(id)

        patientRepository.delete(patient)
    }

    private fun findPatientById(id: Long): Patient {
        return patientRepository.findById(id)
            .orElseThrow {
                PatientNotFoundException(id)
            }
    }

    private fun findSpecialistById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow {
                SpecialistNotFoundException(id)
            }
    }
}