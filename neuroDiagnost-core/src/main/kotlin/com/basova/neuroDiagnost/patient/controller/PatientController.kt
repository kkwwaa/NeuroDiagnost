package com.basova.neuroDiagnost.patient.controller

import com.basova.neuroDiagnost.patient.dto.CreatePatientRequest
import com.basova.neuroDiagnost.patient.dto.PatientResponse
import com.basova.neuroDiagnost.patient.dto.UpdatePatientRequest
import com.basova.neuroDiagnost.patient.service.PatientService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name = "Patients", description = "CRUD для пациентов")
class PatientController(
    private val patientService: PatientService
) {
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Пациент создан")
    fun create(
        @Valid @RequestBody request: CreatePatientRequest
    ): ResponseEntity<PatientResponse> {
        val patient = patientService.create(request)
        return ResponseEntity.ok(patient)
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Пациент найден")
    fun findById(
        @Parameter(description = "ID пациента", example = "1")
        @PathVariable id: Long
    ): ResponseEntity<PatientResponse> {
        return ResponseEntity.ok(patientService.findById(id))
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Пациент обновлён")
    fun update(
        @Parameter(description = "ID пациента", example = "1")
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdatePatientRequest
    ): ResponseEntity<PatientResponse> {
        return ResponseEntity.ok(patientService.update(id, request))
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Пациент удалён")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        patientService.delete(id)
        return ResponseEntity.noContent().build()
    }
}