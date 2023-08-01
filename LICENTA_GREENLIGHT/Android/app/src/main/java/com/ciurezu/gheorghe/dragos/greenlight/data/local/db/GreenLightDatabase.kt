package com.ciurezu.gheorghe.dragos.greenlight.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ciurezu.gheorghe.dragos.greenlight.data.local.dao.FaqDAO
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.FAQ

@Database(
    entities = [
        FAQ::class
    ],
    version = 3
)
abstract class GreenLightDatabase : RoomDatabase() {
    abstract val faqDAO: FaqDAO

    companion object {
        @Volatile
        private var INSTANCE: GreenLightDatabase? = null

        fun getInstance(context: Context): GreenLightDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GreenLightDatabase::class.java,
                    "green_light_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}