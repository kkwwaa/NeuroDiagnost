package com.basova.neuroDiagnost.patient.service

import com.basova.neuroDiagnost.patient.dto.CreatePatientRequest
import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.dto.UpdatePatientRequest
import com.basova.neuroDiagnost.patient.entity.Patient
import com.basova.neuroDiagnost.patient.repository.PatientRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PatientService(
    val patientRepository: PatientRepository
) {
    @Transactional
    fun create(request: CreatePatientRequest): PatientResponse? {
        return null
    }

    fun findById(patientId: Long): PatientResponse? {
        return null
    }

    fun findBySpecialistId(specialistId: Long): PatientResponse? {
        return null
    }

    fun update(request: UpdatePatientRequest): PatientResponse? {
        return null
    }
    
    fun delete(id: Long): Boolean {
        return false
    }
    
    private fun Patient.toResponse(): PatientResponse {
        return PatientResponse(
            id = TODO(),
            specialistId = TODO(),
            fullName = TODO(),
            birthDate = TODO(),
            sex = TODO(),
            parentName = TODO(),
            phone = TODO(),
            anamnesis = TODO(),
            createdAt = TODO()
        )
    }
}