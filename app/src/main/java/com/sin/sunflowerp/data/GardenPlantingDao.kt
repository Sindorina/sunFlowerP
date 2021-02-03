package com.sin.sunflowerp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings():LiveData<List<GardenPlanting>>

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    fun getGardenPlanting(plantId:String):LiveData<GardenPlanting?>

    //同时查询 Plant 和 Garden 表合并结果成为一个数据源

    @Transaction
    @Query("SELECT * FROM plants")
    fun getPlantAndGardenPlantings():LiveData<List<PlantAndGardenPlantings>>

    //把花放到我的花园里面
    @Insert
    fun insertGardenPlanting(gardenPlanting: GardenPlanting)

    //把花从我的花园里面移除
    @Delete
    fun deleteGardenPlanting(gardenPlanting: GardenPlanting)
}