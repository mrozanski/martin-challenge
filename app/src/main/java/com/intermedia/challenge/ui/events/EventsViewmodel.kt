package com.intermedia.challenge.ui.events

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.EventRepository
import kotlinx.coroutines.launch

class EventsViewmodel(private val eventRepository: EventRepository) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>> get() = _events
    val error = MutableLiveData<Int>()


    init {
        loadEvents(0)
    }

    private fun loadEvents(offset: Int) {
        viewModelScope.launch {
            when (val response = eventRepository.getEvents(offset)) {
                is NetResult.Success -> {
                    _events.postValue(response.data.eventsList.events)
                }
                is NetResult.Error -> {
                    error.value = response.error.code()
                }
            }
        }
    }

    fun loadMoreEvents() {
        viewModelScope.launch {
            when (val response = eventRepository.getEvents(_events.value!!.size)) {
                is NetResult.Success -> {
                    val auxList = _events.value!! + response.data.eventsList.events
                    _events.postValue(auxList)
                }
                is NetResult.Error -> {
                    error.value = response.error.code()
                }
            }
        }
    }


}