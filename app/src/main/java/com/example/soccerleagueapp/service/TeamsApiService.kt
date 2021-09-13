package com.example.soccerleagueapp.service

import com.example.soccerleagueapp.league.Team
import com.example.soccerleagueapp.model.TeamsModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TeamsApiService {

    private val baseUrl = "https://613dfb2794dbd600172aba79.mockapi.io/api/"
    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(TeamsAPI::class.java)


    fun getData():Single<TeamsModel>{
        return api.getTeams()
    }
}