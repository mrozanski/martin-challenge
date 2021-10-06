package com.intermedia.challenge.data.services

import com.google.gson.annotations.SerializedName
import com.intermedia.challenge.data.models.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EventService {

    @GET("events")
    suspend fun getEvents(
        @QueryMap auth: HashMap<String, String>,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<EventResponse>
}

data class EventResponse(
    val code: Int = 0,
    @SerializedName("data")
    val eventsList: Eventslist
)

data class Eventslist(
    @SerializedName("results")
    val events: List<Event>
)