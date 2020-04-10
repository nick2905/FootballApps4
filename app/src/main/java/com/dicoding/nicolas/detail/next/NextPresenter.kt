package com.dicoding.nicolas.detail.next

import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.db.TheSportDBApi
import com.dicoding.nicolas.model.EventsResponse
import com.dicoding.nicolas.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextPresenter(
    private val view: NextMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getNextMatch(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getNextMatchs(league)).await(),
                EventsResponse::class.java
            )
            view.hideLoading()
            if (data.events == null) {

            } else {
                view.showNextList(data.events)
            }
        }
    }
}