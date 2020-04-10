package com.dicoding.nicolas.detail

import com.dicoding.nicolas.TestCoroutineContextProvider
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.db.TheSportDBApi
import com.dicoding.nicolas.model.ItemLeague
import com.dicoding.nicolas.model.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailActivityPresenterTest {
    @Mock
    private lateinit var view: DetailView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var presenter: DetailActivityPresenter
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter =
            DetailActivityPresenter(view, apiRepository, gson, TestCoroutineContextProvider())
    }

    @Test
    fun testGetDetailLeague() {
        val league: MutableList<ItemLeague> = mutableListOf()
        val idLeague = "4346"
        val response = LeagueResponse(league)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getDetailLeague(idLeague)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetail(league)
            Mockito.verify(view).hideLoading()
        }
    }
}