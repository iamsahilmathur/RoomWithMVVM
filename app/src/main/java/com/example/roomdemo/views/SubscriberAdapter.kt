package com.example.roomdemo.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.data_source.local.Subscriber
import com.example.roomdemo.databinding.SubscriberViewBinding

class SubscriberAdapter(private val eventListener: EventListener) : RecyclerView.Adapter<SubscriberAdapter.ViewHolder>() {

    private var subscribers = emptyList<Subscriber>()

    fun addAll(subscribers : List<Subscriber>){
        this.subscribers= subscribers
        notifyDataSetChanged()
    }

    interface EventListener{
        fun clickEvent(subscriber: Subscriber)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<SubscriberViewBinding>(LayoutInflater.from(parent.context), R.layout.subscriber_view,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subscriber = subscribers[position]
        holder.bind(subscriber)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

     inner class ViewHolder(private val binding: SubscriberViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(subscriber: Subscriber) {
          binding.nameTxt.text= subscriber.subscriberName
          binding.emailTxt.text= subscriber.subscriberEmail

           binding.root.setOnClickListener {
               if(eventListener != null){
                   eventListener.clickEvent(subscriber)
               }
           }
        }
    }

}