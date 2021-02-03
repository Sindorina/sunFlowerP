package com.sin.sunflowerp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sin.sunflowerp.data.PlantRepository

@Suppress("UNCHECKED_CAST")
class PlantListViewModelFactory(private val plantRepository: PlantRepository) :  ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>) = PlantListViewModel(plantRepository) as T
}