package com.sin.sunflowerp.viewmodels

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.sin.sunflowerp.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*

class PlantAndGardenPlantingsViewModel(plantAndGardenPlantings:PlantAndGardenPlantings) : ViewModel(){
    private val plant = checkNotNull(plantAndGardenPlantings.plant)
    private val gardenPlantings = plantAndGardenPlantings.gardenPlantings[0]
    private val dateFormat by lazy {
        SimpleDateFormat("MMM d, yyyy", Locale.CHINA)
    }

    val wateringDateString = ObservableField(dateFormat.format(gardenPlantings.plantDate.time))
    val wateringInterval = ObservableInt(plant.wateringInterval)
    val imageUrl = ObservableField(plant.imageUrl)
    val plantName = ObservableField(plant.name)
    val plantDateString = ObservableField(dateFormat.format(gardenPlantings.plantDate.time))
}