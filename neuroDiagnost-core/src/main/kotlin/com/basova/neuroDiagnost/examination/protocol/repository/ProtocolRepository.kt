package com.basova.neuroDiagnost.examination.protocol.repository

import com.basova.neuroDiagnost.examination.protocol.entity.Protocol
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProtocolRepository: JpaRepository<Protocol, Long> {
}