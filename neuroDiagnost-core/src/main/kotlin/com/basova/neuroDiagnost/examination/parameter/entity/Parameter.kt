package com.basova.neuroDiagnost.examination.parameter.entity

import com.basova.neuroDiagnost.examination.parameter.enum.ParameterDirection
import com.basova.neuroDiagnost.examination.parameter.enum.ParameterValueType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "parameters")
class Parameter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "value_type", nullable = false)
    var valueType: ParameterValueType,

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false)
    var direction: ParameterDirection,

    @Column(name = "min_value")
    var minValue: Float? = null,

    @Column(name = "max_value")
    var maxValue: Float? = null,

    @Column(name = "weight", nullable = false)
    var weight: Float = 1.0f
)