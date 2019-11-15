package com.grocerystore.grocerystore.repos

import com.grocerystore.grocerystore.models.Aisle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface AisleRepository : JpaRepository<Aisle, Int> {
}