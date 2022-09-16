package com.example.roomdemo.data_source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface SubscriberDao {

    @Query("SELECT * FROM Subscriber")
    fun getAllSubscribers(): LiveData<List<Subscriber>>

    @Insert(onConflict = REPLACE)
    suspend fun insertNewSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM Subscriber")
    suspend fun deleteAllSubscribers()
}