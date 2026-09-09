package com.basova.neuroDiagnost.auth.repository

import com.basova.neuroDiagnost.auth.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}