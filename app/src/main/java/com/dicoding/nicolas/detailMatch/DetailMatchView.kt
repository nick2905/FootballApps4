package com.dicoding.nicolas.detailMatch

import com.dicoding.nicolas.model.ItemEvent
import com.dicoding.nicolas.model.TeamLogo

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(events: List<ItemEvent>?, team: List<TeamLogo>, team2: List<TeamLogo>)
}