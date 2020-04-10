package com.dicoding.nicolas.detail.next

import com.dicoding.nicolas.model.ItemEvent

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextList(next: List<ItemEvent>)
}