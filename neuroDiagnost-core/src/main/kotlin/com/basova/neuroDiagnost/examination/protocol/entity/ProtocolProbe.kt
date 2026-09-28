package com.basova.neuroDiagnost.examination.protocol.entity

import com.basova.neuroDiagnost.examination.probe.entity.Probe
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "protocol_probes")
class ProtocolProbe (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "protocol_id",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_protocol_probes_protocol")
    )
    var protocol: Protocol,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "probe_id",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_protocol_probes_probe")
    )
    var probe: Probe,

    @Column(name = "sort_order", nullable = false)
    var sortOrder: Long,
)