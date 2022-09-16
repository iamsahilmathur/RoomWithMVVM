package com.example.roomdemo.data_source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subscriber(
    @PrimaryKey(autoGenerate = true) var id: Int=0,
    @ColumnInfo(name="subscriberName") var subscriberName : String?,
    @ColumnInfo(name = "subscriberEmail") var subscriberEmail : String?
)