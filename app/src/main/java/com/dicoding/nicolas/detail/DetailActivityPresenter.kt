package com.dicoding.nicolas.detail

import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.db.TheSportDBApi
import com.dicoding.nicolas.model.LeagueResponse
import com.dicoding.nicolas.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivityPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailLeague(idLeague: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val detail = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailLeague(idLeague)).await(),
                LeagueResponse::class.java
            )
            view.hideLoading()
            view.showDetail(
                detail.leagues
            )
        }
    }
}