package com.basova.neuroDiagnost.patient.dto
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Данные для создания пациента")
data class CreatePatientRequest(val name: String) {
}