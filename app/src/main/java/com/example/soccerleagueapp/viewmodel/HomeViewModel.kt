package com.example.soccerleagueapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soccerleagueapp.model.TeamsModel
import com.example.soccerleagueapp.model.TeamsModelItem
import com.example.soccerleagueapp.service.TeamsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val teamApiService = TeamsApiService()
    private val disposable = CompositeDisposable()
    val teams = MutableLiveData<TeamsModel>()

    fun getDataFromApi(){
        disposable.add(
            teamApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TeamsModel>(){
                    override fun onSuccess(t: TeamsModel) {
                        teams.value = t
                    }

                    override fun onError(e: Throwable) {
                    }

                })

        )
    }
}