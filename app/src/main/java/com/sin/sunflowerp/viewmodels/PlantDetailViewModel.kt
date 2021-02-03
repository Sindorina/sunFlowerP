package com.sin.sunflowerp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sin.sunflowerp.data.GardenPlantingRepository
import com.sin.sunflowerp.data.Plant
import com.sin.sunflowerp.data.PlantRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId:String
) :ViewModel(){

    val isPlanted : LiveData<Boolean>
    val plant:LiveData<Plant>
    init {
        val gardenPlanting = gardenPlantingRepository.getGardenPlanting(plantId)
        isPlanted = gardenPlanting.map {
            it != null
        }
        plant = plantRepository.getPlant(plantId)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    fun addPlantToGarden(){
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }
}