package com.dicoding.nicolas.detailMatch

import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.db.TheSportDBApi
import com.dicoding.nicolas.model.AwayResponse
import com.dicoding.nicolas.model.EventsResponse
import com.dicoding.nicolas.model.HomeResponse
import com.dicoding.nicolas.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailMatch(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val detail = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailEvent(idEvent)).await(),
                EventsResponse::class.java
            )

            val imageHome = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(idHomeTeam)).await(),
                HomeResponse::class.java
            )

            val imageAway = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(idAwayTeam)).await(),
                AwayResponse::class.java
            )
                view.hideLoading()
                view.showDetailMatch(
                    detail.events,
                    imageHome.teams,
                    imageAway.teams
                )
        }
    }
}