package com.example.core_settings_data_source.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchItemCache(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "section") val section: String,
    @ColumnInfo(name = "date") val date: Long
)