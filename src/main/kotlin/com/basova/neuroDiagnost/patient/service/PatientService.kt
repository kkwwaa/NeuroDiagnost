package com.basova.neuroDiagnost.patient.service

import com.basova.neuroDiagnost.auth.repository.UserRepository
import com.basova.neuroDiagnost.common.exception.PatientNotFoundException
import com.basova.neuroDiagnost.patient.dto.CreatePatientRequest
import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.dto.UpdatePatientRequest
import com.basova.neuroDiagnost.patient.entity.Patient
import com.basova.neuroDiagnost.patient.repository.PatientRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException

@Service
class PatientService(
    val patientRepository: PatientRepository,
    val userRepository: UserRepository
) {
    @Transactional
    fun create(request: CreatePatientRequest): PatientResponse {
        val specialist = userRepository.findById(request.specialistId)
            .orElseThrow {
                EntityNotFoundException("Specialist not found")
            }

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

        return savedPatient.toResponse()
    }

    fun findById(patientId: Long): PatientResponse {
        val patient = patientRepository.findById(patientId).orElseThrow {
            PatientNotFoundException(patientId)
        }

        return patient.toResponse()
    }

    fun findBySpecialistId(specialistId: Long): List<PatientResponse> {
        val patients = patientRepository.findAllBySpecialistId(specialistId).map {
            it.toResponse()
        }

        return patients
    }

    @Transactional
    fun update(id: Long, request: UpdatePatientRequest): PatientResponse {
        val patient = patientRepository.findById(id)
            .orElseThrow {
                PatientNotFoundException(id)
            }

        patient.fullName = request.fullName.trim()
        patient.birthDate = request.birthDate
        patient.sex = request.sex
        patient.parentName = request.parentName?.trim()
        patient.phone = request.phone?.trim()
        patient.anamnesis = request.anamnesis?.trim()

        return patient.toResponse()
    }

    @Transactional
    fun delete(id: Long) {
        val patient = patientRepository.findById(id).orElseThrow{
            PatientNotFoundException(id)
        }

        patientRepository.delete(patient)
    }

    private fun Patient.toResponse(): PatientResponse {
        return PatientResponse(
            id = requireNotNull(id),
            fullName = fullName,
            birthDate = birthDate,
            sex = sex,
            parentName = parentName,
            phone = phone,
            anamnesis = anamnesis,
            specialistId = specialist.id!!,
            createdAt = createdAt
        )
    }
}