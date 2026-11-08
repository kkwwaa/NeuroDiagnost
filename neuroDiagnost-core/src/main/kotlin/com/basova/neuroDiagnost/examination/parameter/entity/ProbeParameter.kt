package com.basova.neuroDiagnost.examination.parameter.entity

import com.basova.neuroDiagnost.examination.probe.entity.Probe
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "probe_parameters")
class ProbeParameter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "probe_id", nullable = false)
    var probe: Probe,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parameter_id", nullable = false)
    var parameter: Parameter,

    @Column(name = "sort_order", nullable = false)
    var sortOrder: Int
)