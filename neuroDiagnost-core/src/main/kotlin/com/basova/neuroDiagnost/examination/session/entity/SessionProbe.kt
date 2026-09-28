package com.basova.neuroDiagnost.examination.session.entity

import com.basova.neuroDiagnost.examination.probe.entity.Probe
import com.basova.neuroDiagnost.examination.session.enum.SessionProbeStatus
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
import java.time.LocalDateTime

@Entity
@Table(name = "session_probes")
class SessionProbe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "session_id",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_session_probes_session")
    )
    var session: Session,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "probe_id",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_session_probes_probe")
    )
    var probe: Probe,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: SessionProbeStatus,

    @Column(columnDefinition = "TEXT")
    var note: String? = null,

    @Column(name = "started_at")
    var startedAt: LocalDateTime? = null,

    @Column(name = "completed_at")
    var completedAt: LocalDateTime? = null,
)