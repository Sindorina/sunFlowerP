package com.sin.sunflowerp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.sin.sunflowerp.data.GardenPlantingRepository

class GardenPlantingListViewModel(gardenPlantingRepository: GardenPlantingRepository) :ViewModel() {

    val plantAndGardenPlantings = gardenPlantingRepository.getPlantAndGardenPlantings().map {
        it.filter { p->
            p.gardenPlantings.isNotEmpty()
        }
    }

    val gardenPlantings = gardenPlantingRepository.getGardenPlantings()
}