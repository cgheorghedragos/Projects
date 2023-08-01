package com.ciurezu.gheorghe.dragos.greenlight.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.FAQ
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.Flow

@Dao
interface FaqDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFaqs(faq: FAQ): Long

    @Query("SELECT * FROM faq_table")
    suspend fun getFaqs(): List<FAQ>

    @Query("DELETE FROM faq_table")
    suspend fun deleteAllFaqs() : Int
}