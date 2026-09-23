package com.basova.neuroDiagnost.examination.session.repository

import com.basova.neuroDiagnost.examination.session.entity.SessionProbe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionProbeRepository: JpaRepository<SessionProbe, Long> {
    fun findAllBySessionId(sessionId: Long): List<SessionProbe>
}