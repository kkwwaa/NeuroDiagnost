package com.basova.neuroDiagnost.auth.entity

import com.basova.neuroDiagnost.auth.enum.Role
import com.basova.neuroDiagnost.patient.entity.Patient
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "full_name")
    var fullName: String? = null,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(name = "password_hash", nullable = false)
    var passwordHash: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(
        mappedBy = "specialist",
        fetch = FetchType.LAZY,
    )
    val patients: MutableList<Patient> = mutableListOf(),
    ) {

}