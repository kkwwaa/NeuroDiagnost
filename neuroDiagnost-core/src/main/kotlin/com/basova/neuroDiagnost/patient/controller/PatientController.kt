package com.basova.neuroDiagnost.patient.controller

import com.basova.neuroDiagnost.patient.dto.CreatePatientRequest
import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.dto.UpdatePatientRequest
import org.springframework.http.ResponseEntity

interface PatientController {
    fun create(request: CreatePatientRequest): ResponseEntity<PatientResponse>

    fun findById(id: Long): ResponseEntity<PatientResponse>

    fun findBySpecialistId(specialistId: Long): ResponseEntity<List<PatientResponse>>

    fun update(id: Long, request: UpdatePatientRequest): ResponseEntity<PatientResponse>

    fun delete(id: Long): ResponseEntity<Void>
}