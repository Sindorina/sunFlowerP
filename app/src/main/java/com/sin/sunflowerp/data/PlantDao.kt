package com.sin.sunflowerp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlantDao {

    //获取所有植物
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants():LiveData<List<Plant>>

    //根据 id 获取植物
    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId:String):LiveData<Plant>

    //获取生长期内的所有植物
    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber:Int):LiveData<List<Plant>>

    //初始化插入所有预设数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants:List<Plant>)

}