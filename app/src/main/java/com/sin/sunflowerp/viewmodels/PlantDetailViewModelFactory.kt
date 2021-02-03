package com.sin.sunflowerp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sin.sunflowerp.data.GardenPlantingRepository
import com.sin.sunflowerp.data.PlantRepository

class PlantDetailViewModelFactory(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        PlantDetailViewModel(plantRepository,gardenPlantingRepository,plantId) as T
}