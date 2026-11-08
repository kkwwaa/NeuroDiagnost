package com.basova.neuroDiagnost.examination.parameter.repository

import com.basova.neuroDiagnost.examination.parameter.entity.ProbeParameter
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProbeParameterRepository : JpaRepository<ProbeParameter, Long> {

    @EntityGraph(attributePaths = ["parameter"])
    fun findAllByProbeIdOrderBySortOrder(probeId: Long): List<ProbeParameter>
}