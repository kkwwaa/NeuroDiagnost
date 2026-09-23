package com.basova.neuroDiagnost.patient.mapper

import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.entity.Patient

object PatientMapper {

    fun toResponse(patient: Patient): PatientResponse {
        return PatientResponse(
            id = requireNotNull(patient.id),
            fullName = patient.fullName,
            birthDate = patient.birthDate,
            sex = patient.sex,
            parentName = patient.parentName,
            phone = patient.phone,
            anamnesis = patient.anamnesis,
            specialistId = requireNotNull(patient.specialist.id),
            createdAt = patient.createdAt
        )
    }
}
