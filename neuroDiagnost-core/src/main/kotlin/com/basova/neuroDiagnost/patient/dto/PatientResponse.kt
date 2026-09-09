package com.basova.neuroDiagnost.patient.dto

import com.basova.neuroDiagnost.patient.enum.Sex
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

@Schema(description = "Пациент")
data class PatientResponse(
    @Schema(description = "ID пациента", example = "1", required = true)
    val id: Long,

    @Schema(description = "ID специалиста, которому принадлежит пациент", example = "1", required = true)
    val specialistId: Long,

    @Schema(description = "ФИО пациента", example = "Иванов Иван Иванович", required = true)
    val fullName: String,

    @Schema(description = "Дата рождения пациента", example = "2018-05-12", required = true)
    val birthDate: LocalDate,

    @Schema(description = "Пол пациента", example = "MALE", required = false)
    val sex: Sex?,

    @Schema(description = "ФИО родителя или законного представителя", example = "Иванова Анна Сергеевна", required = false)
    val parentName: String?,

    @Schema(description = "Телефон родителя или законного представителя", example = "+79991234567", required = false)
    val phone: String?,

    @Schema(description = "Анамнез пациента", example = "Особенности раннего развития...", required = false)
    val anamnesis: String?,

    @Schema(description = "Дата создания записи пациента", example = "2024-05-12T10:15:30", required = true)
    val createdAt: LocalDateTime
)