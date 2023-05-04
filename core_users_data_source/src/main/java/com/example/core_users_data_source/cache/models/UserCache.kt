package com.example.core_users_data_source.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserCache(
    @PrimaryKey @ColumnInfo(name = "local_id") val localId: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "id_token") val idToken: String,
    @ColumnInfo(name = "refresh_token") val refreshToken: String,
    @ColumnInfo(name = "expires_in") val expiresIn: String
)
