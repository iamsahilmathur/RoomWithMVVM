package com.example.roomdemo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.R
import com.example.roomdemo.data_source.Repository
import com.example.roomdemo.data_source.local.Subscriber
import com.example.roomdemo.data_source.local.SubscriberDatabase
import com.example.roomdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(applicationContext)?.subscribeDao()
        val repository= dao?.let { Repository(it) }
        val viewModelFactory = MainViewModelFactory(repository!!)
        val viewModel : MainViewModel= ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        binding.mainViewModel= viewModel
        binding.lifecycleOwner= this


        val mAdapter= SubscriberAdapter(object : SubscriberAdapter.EventListener{
            override fun clickEvent(subscriber: Subscriber) {
                subscriber?.let {
                    binding.nameEdt.setText(it.subscriberName)
                    binding.emailEdt.setText(it.subscriberEmail)
                    viewModel.id= it.id
                    viewModel.isSave= true
                    viewModel.saveOrUpdateBtnText.value= "Update"
                    viewModel.clearAllAndDeleteBtnText.value= "Delete"
                }
            }
        })

        binding.subscriberRv.adapter= mAdapter

        viewModel.subscribers.observe(this, Observer {
            mAdapter.addAll(it)
        })

        viewModel.showToastEvent.observe(this, Observer {
            Toast.makeText(this@MainActivity,it.peekContent(),Toast.LENGTH_SHORT).show()
        })

    }
}