package com.example.core_settings_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_settings_data_source.models.SearchItemCache
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Query("SELECT * FROM search_table WHERE section == :section ORDER BY date DESC")
    fun observeSearchItem(section: String): Flow<List<SearchItemCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearchItem(task: SearchItemCache)

}