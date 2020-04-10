package com.dicoding.nicolas.detail.prev

import com.dicoding.nicolas.model.ItemEvent

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPrevList(data: List<ItemEvent>)
}