package com.basova.neuroDiagnost.patient.dto
import com.basova.neuroDiagnost.patient.enum.Sex
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Schema(description = "Данные для создания пациента")
data class CreatePatientRequest(

    @field:NotNull
    @Schema(
        description = "ID специалиста, которому принадлежит пациент",
        example = "1"
    )
    val specialistId: Long?,

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
    val birthDate: LocalDate?,

    @field:Size(max = 32)
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
)

