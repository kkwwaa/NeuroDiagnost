package com.basova.neuroDiagnost.patient.dto

import com.basova.neuroDiagnost.patient.enum.Sex
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

@Schema(description = "Пациент")
data class PatientResponse(
    @Schema(example = "1")
    val id: Long,

    @Schema(example = "1")
    val specialistId: Long,

    @Schema(example = "Иванов Иван Иванович")
    val fullName: String,

    @Schema(example = "2018-05-12")
    val birthDate: LocalDate,

    @Schema(example = "MALE")
    val sex: Sex?,

    @Schema(example = "Иванова Анна Сергеевна")
    val parentName: String?,

    @Schema(example = "+79991234567")
    val phone: String?,

    val anamnesis: String?,

    val createdAt: LocalDateTime
) {
}