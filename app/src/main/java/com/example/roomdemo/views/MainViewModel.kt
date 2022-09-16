package com.example.roomdemo.views
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.data_source.Repository
import com.example.roomdemo.data_source.local.Subscriber
import com.example.roomdemo.utilities.Event
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel() {

    val subscribers= repository.subscribers
    var id=-1
    var isSave = false

    var name = MutableLiveData<String>()
    var email= MutableLiveData<String>()

    private val _showToastEvent = MutableLiveData<Event<String>>()

    val showToastEvent : LiveData<Event<String>>
        get() = _showToastEvent


    var saveOrUpdateBtnText = MutableLiveData<String>("Save")
    var clearAllAndDeleteBtnText= MutableLiveData<String>("Clear All")


    fun saveOrUpdateBtn(){

        viewModelScope.launch {
            if(isSave){
                val status = repository.updateSubscribers(Subscriber(id,name.value,email.value))

                if(status > -1){
                    _showToastEvent.value= Event("Subscriber updated.")
                    isSave=false
                    saveOrUpdateBtnText.value="Save"
                    clearAllAndDeleteBtnText.value="Clear All"
                    clearEditText()
                }

            }else{
                val status : Long= repository.insertNewSubscriber(Subscriber(0,name.value!!,email.value))
                 if(status > -1){
                     _showToastEvent.value= Event("New Subscriber added.")
                     clearEditText()
                 }

            }
        }
    }

    fun clearAllOrDeleteBtn(){
        val selectedSubscriber= Subscriber(id,name.value,email.value)
        viewModelScope.launch {
            if(isSave){
                val status =repository.deleteSubscriber(selectedSubscriber)
                if(status > -1){
                    isSave=false
                    saveOrUpdateBtnText.value="Save"
                    clearAllAndDeleteBtnText.value="Clear All"
                    _showToastEvent.value= Event("Subscriber deleted.")
                    clearEditText()
                }
            }else{
                repository.deleteAllSubscribers()
                _showToastEvent.value= Event("All Subscriber have been removed.")
                clearEditText()
            }
        }
    }

    private fun clearEditText(){
        name.value=""
        email.value=""
    }


}