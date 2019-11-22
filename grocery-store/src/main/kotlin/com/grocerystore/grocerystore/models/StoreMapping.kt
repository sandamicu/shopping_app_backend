package com.grocerystore.grocerystore.models

import javax.annotation.Generated
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity(name="store_mapping")
data class StoreMapping(
        @Id @Generated val id :Int,
        @get: NotBlank val column: Int,
        @get: NotBlank val row : Int,
        @get: NotBlank val value : Int
        )
