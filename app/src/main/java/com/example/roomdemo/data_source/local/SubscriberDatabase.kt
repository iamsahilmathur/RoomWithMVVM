package com.example.roomdemo.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1, exportSchema = false)
abstract class SubscriberDatabase : RoomDatabase() {
    abstract fun subscribeDao(): SubscriberDao



    companion object{
        var subscribeDb : SubscriberDatabase?= null

        fun getInstance(applicationContext : Context) : SubscriberDatabase?{
            if(subscribeDb == null){
                subscribeDb=  Room.databaseBuilder(
                    applicationContext,
                    SubscriberDatabase::class.java, "subscriber_db"
                ).build()
            }

            return subscribeDb
        }


    }


}