package com.grocerystore.grocerystore.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.grocerystore.grocerystore.repos.AisleRepository
import com.grocerystore.grocerystore.models.Aisle
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@RestController
@RequestMapping("/api")
class AisleController(@Autowired private val aisleRepository: AisleRepository){


    @GetMapping("/aisles")
    fun getAllAisles() : List<Aisle> = aisleRepository.findAll()
}