package com.dicoding.nicolas.detail

import com.dicoding.nicolas.model.ItemLeague

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetail(leagues: List<ItemLeague>?)
}