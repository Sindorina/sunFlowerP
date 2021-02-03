package com.sin.sunflowerp.data

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GardenPlantingRepository private constructor(private val gardenPlantingDao: GardenPlantingDao){

    //把花放入我的花园
    suspend fun createGardenPlanting(plantId:String){
        withContext(IO){
            val gardenPlanting = GardenPlanting(plantId)
            gardenPlantingDao.insertGardenPlanting(gardenPlanting)
        }
    }
    //把花从我的花园移除
    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting){
        withContext(IO){
            gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
        }
    }

    fun getGardenPlanting(plantId: String) =  gardenPlantingDao.getGardenPlanting(plantId)

    fun getGardenPlantings() =  gardenPlantingDao.getGardenPlantings()

    fun getPlantAndGardenPlantings() = gardenPlantingDao.getPlantAndGardenPlantings()

    companion object{
        @Volatile
        private var instance:GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao):GardenPlantingRepository{
            return instance?: synchronized(this){
                GardenPlantingRepository(gardenPlantingDao).also {
                    instance = it
                }
            }
        }
    }
}