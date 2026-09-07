package com.basova.neuroDiagnost.patient.dto

import com.basova.neuroDiagnost.patient.enum.Sex
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UpdatePatientRequest(

    @field:NotBlank
    @field:Size(max = 255)
    @Schema(
        description = "ФИО пациента",
        example = "Иванов Иван Иванович"
    )
    val fullName: String,

    @field:NotNull
    @field:PastOrPresent
    @Schema(
        description = "Дата рождения",
        example = "2018-05-12"
    )
    val birthDate: LocalDate,

    @Schema(
        description = "Пол",
        example = "MALE"
    )
    val sex: Sex? = null,

    @field:Size(max = 255)
    @Schema(
        description = "ФИО родителя или законного представителя",
        example = "Иванова Анна Сергеевна"
    )
    val parentName: String? = null,

    @field:Size(max = 32)
    @Schema(
        description = "Телефон родителя",
        example = "+79991234567"
    )
    val phone: String? = null,

    @field:Size(max = 10000)
    @Schema(
        description = "Анамнез",
        example = "Особенности раннего развития..."
    )
    val anamnesis: String? = null
) {
}