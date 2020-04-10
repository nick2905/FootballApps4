package com.dicoding.nicolas.detail.next

import com.dicoding.nicolas.TestCoroutineContextProvider
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.model.EventsResponse
import com.dicoding.nicolas.model.HomeResponse
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

class NextPresenterTest{
    @Mock
    private lateinit var view: NextMatchView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var presenter: NextPresenter
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = NextPresenter(view,apiRepository,gson,TestCoroutineContextProvider())
    }

    @Test
    fun testGetNextList(){
        val league: MutableList<ItemEvent> = mutableListOf()
        val idLeague = "4346"
        val response = EventsResponse(league)
        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatch(idLeague)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showNextList(league)
            Mockito.verify(view).hideLoading()
        }
    }
}