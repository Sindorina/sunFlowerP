package com.sin.sunflowerp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sin.sunflowerp.data.GardenPlantingRepository

class GardenPlantingListViewModelFactory(private val gardenPlantingRepository: GardenPlantingRepository) :ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = GardenPlantingListViewModel(gardenPlantingRepository) as T
}