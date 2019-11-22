package com.grocerystore.grocerystore.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank


@Entity(name="aisle")

data class Aisle(
        @Id @GeneratedValue val id : Int ,
        @get: NotBlank val name : String ,
        @get: NotBlank val icon : String
//        @get: NotBlank val row : Int ,
//        @get: NotBlank val column: Int
){}



