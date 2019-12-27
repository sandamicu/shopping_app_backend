package com.grocerystore.grocerystore.controllers

import com.grocerystore.grocerystore.Path
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.grocerystore.grocerystore.models.StoreMapping
import com.grocerystore.grocerystore.repos.StoreMappingRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/api")
class StoreMappingController(@Autowired private val storeMappingRepository: StoreMappingRepository){


    @GetMapping("/store_mappings")
    fun getAllAisles() : List<StoreMapping> = storeMappingRepository.findAll()

    @GetMapping("/path")
    fun getShortestPath(@RequestBody start: StoreMapping, @RequestBody points : List<StoreMapping>): List<StoreMapping>{

        val path = Path(getRow()+1, getColumn()+1)
        return path.getPath(storeMappingRepository.findAll(), start, points)

    }

    fun getRow(): Int{
        var row = 0
        storeMappingRepository.findAll().forEach{
            if(it.row > row)
                row = it.row
        }
        return row
    }

    fun getColumn(): Int{
        var column = 0
        storeMappingRepository.findAll().forEach{
            if(it.column > column)
                column = it.row
        }
        return column
    }


}
