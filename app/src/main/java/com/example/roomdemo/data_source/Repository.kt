package com.example.roomdemo.data_source

import com.example.roomdemo.data_source.local.Subscriber
import com.example.roomdemo.data_source.local.SubscriberDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext


class Repository(private val dao: SubscriberDao) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insertNewSubscriber(subscriber: Subscriber) =
        withContext(IO) { return@withContext dao.insertNewSubscriber(subscriber) }

    suspend fun deleteSubscriber(subscriber: Subscriber) =
        withContext(IO) { return@withContext dao.deleteSubscriber(subscriber) }

    suspend fun deleteAllSubscribers() =
        withContext(IO) { dao.deleteAllSubscribers() }

    suspend fun updateSubscribers(subscriber: Subscriber) =
        withContext(IO) { return@withContext dao.updateSubscriber(subscriber) }

}