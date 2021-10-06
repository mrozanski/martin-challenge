package com.intermedia.challenge.data.repositories

import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.services.EventResponse
import com.intermedia.challenge.data.services.EventService

class EventRepository(
    private val eventService: EventService
) : BaseRepository() {

    suspend fun getEvents(offset: Int, limit: Int = 15): NetResult<EventResponse> =
        handleResult(eventService.getEvents(authParams.getMap(), offset, limit))

}