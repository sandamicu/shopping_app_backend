package com.grocerystore.grocerystore.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.grocerystore.grocerystore.repos.AisleRepository
import com.grocerystore.grocerystore.models.Aisle
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/api")
class AisleController(@Autowired private val aisleRepository: AisleRepository){


    @GetMapping("/aisles")
    fun getAllAisles() : List<Aisle> = aisleRepository.findAll()
}
