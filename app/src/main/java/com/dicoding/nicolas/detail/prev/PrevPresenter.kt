package com.dicoding.nicolas.detail.prev

import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.db.TheSportDBApi
import com.dicoding.nicolas.model.EventsResponse
import com.dicoding.nicolas.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PrevPresenter(
    private val view: PrevMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPrevMatch(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPrevMatchs(league)).await(),
                EventsResponse::class.java
            )
            view.hideLoading()
            if (data.events == null) {
            } else {
                view.showPrevList(data.events)

            }
        }
    }
}