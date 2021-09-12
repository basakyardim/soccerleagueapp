package com.example.soccerleagueapp.service


import com.example.soccerleagueapp.model.TeamsModelItem
import io.reactivex.Single
import retrofit2.http.GET

interface TeamsAPI {

    @GET("teams")
    fun getTeams():Single<List<TeamsModelItem>>
}