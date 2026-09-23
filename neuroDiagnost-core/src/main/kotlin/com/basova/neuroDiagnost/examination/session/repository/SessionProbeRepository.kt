package com.basova.neuroDiagnost.examination.session.repository

import com.basova.neuroDiagnost.examination.session.entity.SessionProbe
import com.basova.neuroDiagnost.patient.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionProbeRepository: JpaRepository<Patient, Long> {
    fun saveAll(sessionProbeList: List<SessionProbe>)
}