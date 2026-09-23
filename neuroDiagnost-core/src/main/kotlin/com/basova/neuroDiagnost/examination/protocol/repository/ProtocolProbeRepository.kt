package com.basova.neuroDiagnost.examination.protocol.repository

import com.basova.neuroDiagnost.examination.protocol.entity.Protocol
import com.basova.neuroDiagnost.examination.protocol.entity.ProtocolProbe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProtocolProbeRepository: JpaRepository<ProtocolProbe, Int> {

    fun findAllByProtocolIdOrderBySortOrder(protocolId: Long): List<ProtocolProbe>
}