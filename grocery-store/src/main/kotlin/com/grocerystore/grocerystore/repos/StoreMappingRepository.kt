package com.grocerystore.grocerystore.repos

import com.grocerystore.grocerystore.models.StoreMapping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreMappingRepository: JpaRepository<StoreMapping, Int> {
}
