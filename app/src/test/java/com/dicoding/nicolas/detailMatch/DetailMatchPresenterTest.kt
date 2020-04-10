package com.dicoding.nicolas.detailMatch

import com.dicoding.nicolas.TestCoroutineContextProvider
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.model.*
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

class DetailMatchPresenterTest {
    @Mock
    private lateinit var view: DetailMatchView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var presenter: DetailMatchPresenter
    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view,apiRepository,gson, TestCoroutineContextProvider())
    }

    @Test
    fun testGetDetailEvent(){
        val events: MutableList<ItemEvent> = mutableListOf()
        val homeTeam: MutableList<TeamLogo> = mutableListOf()
        val awayTeam: MutableList<TeamLogo> = mutableListOf()

        val idLeague = "4346"
        val homeId = "134149"
        val awayId = "134148"

        val response = EventsResponse(events)
        val homeResponse = HomeResponse(homeTeam)
        val awayResponse = AwayResponse(awayTeam)

        runBlocking{
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsResponse::class.java
                )
            ).thenReturn(response)

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    HomeResponse::class.java
                )
            ).thenReturn(homeResponse)

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    awayResponse::class.java
                )
            ).thenReturn(awayResponse)

            presenter.getDetailMatch(idLeague, homeId, awayId)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailMatch(events, homeTeam, awayTeam)
            Mockito.verify(view).hideLoading()
        }
    }
}