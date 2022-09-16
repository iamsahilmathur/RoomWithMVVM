package com.example.roomdemo.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.data_source.Repository

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}