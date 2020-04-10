package com.dicoding.nicolas.search

import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.db.TheSportDBApi
import com.dicoding.nicolas.model.EventResponse
import com.dicoding.nicolas.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getEvent(nameEvent: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getSearchEvent(nameEvent)).await(),
                EventResponse::class.java
            )

            view.hideLoading()
            if (data.event == null) {
            } else {
                view.showSearchMatch(data.event.filter {
                    it.strSport == "Soccer"
                })
            }
        }
    }
}

