package com.grocerystore.grocerystore.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.grocerystore.grocerystore.models.StoreMapping
import com.grocerystore.grocerystore.repos.StoreMappingRepository
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/api")
class StoreMappingController(@Autowired private val storeMappingRepository: StoreMappingRepository){


    @GetMapping("/store_mappings")
    fun getAllAisles() : List<StoreMapping> = storeMappingRepository.findAll()
}
