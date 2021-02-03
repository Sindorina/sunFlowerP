package com.sin.sunflowerp.data

import androidx.room.Embedded
import androidx.room.Relation

class PlantAndGardenPlantings {

    @Embedded
    lateinit var plant: Plant

    @Relation(parentColumn = "id",entityColumn = "plant_id")
    var gardenPlantings:List<GardenPlanting> = arrayListOf()
}