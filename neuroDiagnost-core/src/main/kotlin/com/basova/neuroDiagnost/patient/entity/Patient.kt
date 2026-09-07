package com.basova.neuroDiagnost.patient.entity

import com.basova.neuroDiagnost.auth.entity.User
import com.basova.neuroDiagnost.patient.enum.Sex
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
import java.time.LocalDateTime

@Entity
@Table(name = "patients")
class Patient (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialist_id", nullable = false, foreignKey = ForeignKey(name = "fk_patients_specialist"))
    var specialist: User,

    @Column(name = "full_name", nullable = false)
    var fullName: String,

    @Column(name = "birth_date", nullable = false)
    var birthDate: LocalDate,

    @Column
    @Enumerated(EnumType.STRING)
    var sex: Sex? = null,

    @Column(name = "parent_name")
    var parentName: String? = null,

    @Column
    var phone: String? = null,

    @Column(columnDefinition = "TEXT")
    var anamnesis: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    )
{
}