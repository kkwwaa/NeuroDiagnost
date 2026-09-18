package com.basova.neuroDiagnost.examination.session.repository

import com.basova.neuroDiagnost.examination.session.entity.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository : JpaRepository<Session, Long> {
}