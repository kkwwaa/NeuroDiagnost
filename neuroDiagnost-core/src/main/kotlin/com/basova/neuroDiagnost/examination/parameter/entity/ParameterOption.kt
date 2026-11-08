package com.basova.neuroDiagnost.examination.parameter.entity

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
@Table(name = "parameter_options")
class ParameterOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parameter_id", nullable = false)
    var parameter: Parameter,

    @Column(name = "value", nullable = false)
    var value: String,

    @Column(name = "score", nullable = false)
    var score: Double,

    @Column(name = "sort_order", nullable = false)
    var sortOrder: Int
)