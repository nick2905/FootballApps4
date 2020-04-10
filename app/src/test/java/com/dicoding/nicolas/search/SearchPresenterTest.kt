package com.dicoding.nicolas.search

import com.dicoding.nicolas.TestCoroutineContextProvider
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.model.EventResponse
import com.dicoding.nicolas.model.ItemEvent
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

class SearchPresenterTest{
    @Mock
    private lateinit var view: SearchMatchView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var presenter: SearchPresenter
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view,apiRepository,gson, TestCoroutineContextProvider())
    }

    @Test
    fun testGetSearchList(){
        val event: MutableList<ItemEvent> = mutableListOf()
        val idLeague = "4346"
        val response = EventResponse(event)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getEvent(idLeague)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showSearchMatch(event)
            Mockito.verify(view).hideLoading()
        }
    }
}