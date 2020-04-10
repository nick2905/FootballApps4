package com.dicoding.nicolas.favorite.FavPrev

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.nicolas.UI.UIItemPrevMatch
import com.dicoding.nicolas.model.FavoriteModelLast
import com.dicoding.nicolas.utils.changeDateFormat
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FavPrevAdapter(private val matchs: List<FavoriteModelLast>, private val listener: (FavoriteModelLast) -> Unit) :
    RecyclerView.Adapter<FavNextMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavNextMatchViewHolder =
        FavNextMatchViewHolder(
            UIItemPrevMatch().createView(
                AnkoContext.Companion.create(parent.context)
            )
        )

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: FavNextMatchViewHolder, position: Int) {
        holder.bindItem(matchs[position], listener)
    }
}

class FavNextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameHomeTeam: TextView = view.find(UIItemPrevMatch.nameHomeTeam)
    private val nameAwayTeam: TextView = view.find(UIItemPrevMatch.nameAwayTeam)
    private val scoreHomeTeam: TextView = view.find(UIItemPrevMatch.scoreHomeTeam)
    private val scoreAwayTeam: TextView = view.find(UIItemPrevMatch.scoreAwayTeam)
    private val dateEvent: TextView = view.find(UIItemPrevMatch.dateEvent)
    fun bindItem(matchs: FavoriteModelLast, listener: (FavoriteModelLast) -> Unit) {
        val dateChange = matchs.dateEvent
        val timeChange = matchs.strTime!!
        nameHomeTeam.text = matchs.strHomeTeam ?: "-"
        nameAwayTeam.text = matchs.strAwayTeam ?: "-"
        scoreHomeTeam.text = matchs.intHomeScore ?: "-"
        scoreAwayTeam.text = matchs.intAwayScore ?: "-"
        dateEvent.text = changeDateFormat(dateChange, timeChange)
        itemView.setOnClickListener { listener(matchs) }
    }
}