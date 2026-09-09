package com.basova.neuroDiagnost.patient.repository

import com.basova.neuroDiagnost.patient.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface PatientRepository: JpaRepository<Patient, Long> {
    fun findAllBySpecialistId(@Param("specialistId") specialistId: Long): List<Patient>
}