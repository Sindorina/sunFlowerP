package com.sin.sunflowerp.utils

import android.content.Context
import com.sin.sunflowerp.data.AppDatabase
import com.sin.sunflowerp.data.GardenPlantingRepository
import com.sin.sunflowerp.data.PlantRepository
import com.sin.sunflowerp.viewmodels.GardenPlantingListViewModelFactory
import com.sin.sunflowerp.viewmodels.PlantDetailViewModelFactory
import com.sin.sunflowerp.viewmodels.PlantListViewModel
import com.sin.sunflowerp.viewmodels.PlantListViewModelFactory

object InjectorUtils {

    fun providePlantListViewModelFactory(context: Context) =
        PlantListViewModelFactory(getPlantRepository(context))

    private fun getGardenPlantingRepository(context: Context) =
        GardenPlantingRepository.getInstance(AppDatabase.getInstance(context).gardenPlantingDao())


    private fun getPlantRepository(context: Context) =
        PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())

    fun providePlantDetailViewModelFactory(context: Context,plantId:String) =
        PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context), plantId)

    fun provideGardenPlantingListViewModelFactory(context: Context) =
        GardenPlantingListViewModelFactory(getGardenPlantingRepository(context))

}