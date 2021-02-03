package com.sin.sunflowerp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.sin.sunflowerp.data.PlantRepository

class PlantListViewModel internal constructor(plantRepository: PlantRepository):ViewModel(){

    private val growZoneNumber  = MutableLiveData<Int>().apply {
        value = NO_GROW_ZONE
    }

    val plants = growZoneNumber.switchMap {
        if (it == NO_GROW_ZONE){
            plantRepository.getPlants()
        }else{
            plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(number:Int){
        growZoneNumber.value = number
    }

    fun clearGrowZoneNumber(){
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE

    companion object{
        const val NO_GROW_ZONE = -1
    }

}