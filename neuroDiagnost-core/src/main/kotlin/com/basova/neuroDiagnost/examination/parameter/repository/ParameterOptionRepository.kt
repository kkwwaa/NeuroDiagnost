package com.basova.neuroDiagnost.examination.parameter.repository

import com.basova.neuroDiagnost.examination.parameter.entity.ParameterOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParameterOptionRepository : JpaRepository<ParameterOption, Long> {

    fun findAllByParameterIdInOrderByParameterIdAscSortOrderAsc(
        parameterIds: Collection<Long>
    ): List<ParameterOption>
}