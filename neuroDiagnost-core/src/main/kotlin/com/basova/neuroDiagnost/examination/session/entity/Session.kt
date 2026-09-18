package com.basova.neuroDiagnost.examination.session.entity

import com.basova.neuroDiagnost.examination.protocol.entity.Protocol
import com.basova.neuroDiagnost.examination.session.enum.SessionStatus
import com.basova.neuroDiagnost.patient.entity.Patient
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "sessions")
class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "patient_id",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_sessions_patient")
    )
    var patient: Patient,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "protocol_id",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_sessions_protocol")
    )
    var protocol: Protocol,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: SessionStatus,

    @Column(name = "examination_date", nullable = false)
    var examinationDate: LocalDate,
)