package com.basova.neuroDiagnost.patient.service

import com.basova.neuroDiagnost.patient.dto.CreatePatientRequest
import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.dto.UpdatePatientRequest

interface PatientService {

    fun create(request: CreatePatientRequest): PatientResponse

    fun findById(patientId: Long): PatientResponse

    fun findBySpecialistId(specialistId: Long): List<PatientResponse>

    fun update(id: Long, request: UpdatePatientRequest): PatientResponse

    fun delete(id: Long)
}