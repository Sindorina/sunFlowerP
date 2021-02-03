package com.sin.sunflowerp.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sin.sunflowerp.data.AppDatabase
import com.sin.sunflowerp.data.Plant
import com.sin.sunflowerp.utils.PLANT_DATA_FILENAME
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(context: Context,workerParameters: WorkerParameters)
    :CoroutineWorker(context,workerParameters){
    override suspend fun doWork(): Result {
        return coroutineScope {
            try {
                applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream->
                    val plantType = object:TypeToken<List<Plant>>(){}.type
                    val plantList:List<Plant> = Gson().fromJson(inputStream.reader(),plantType)
                    val db = AppDatabase.getInstance(applicationContext)
                    db.plantDao().insertAll(plantList)
                }
                Result.success()
            }catch (e:Exception){
                Result.failure()
            }
        }
    }
}