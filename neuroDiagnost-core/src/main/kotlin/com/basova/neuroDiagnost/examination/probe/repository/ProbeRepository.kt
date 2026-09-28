package com.basova.neuroDiagnost.examination.probe.repository

import com.basova.neuroDiagnost.examination.probe.entity.Probe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProbeRepository: JpaRepository<Probe, Long> {
}