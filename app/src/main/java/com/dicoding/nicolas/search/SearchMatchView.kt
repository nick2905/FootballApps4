package com.dicoding.nicolas.search

import com.dicoding.nicolas.model.ItemEvent

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatch(data: List<ItemEvent>)
}