package com.dicoding.nicolas.favorite.FavNext

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.nicolas.UI.UIItemPrevMatch
import com.dicoding.nicolas.model.FavoriteModelNext
import com.dicoding.nicolas.utils.changeDateFormat
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FavNextAdapter(private val matchs: List<FavoriteModelNext>, private val listener: (FavoriteModelNext) -> Unit) :
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
    fun bindItem(matchs: FavoriteModelNext, listener: (FavoriteModelNext) -> Unit) {
        val dateChange = matchs.dateEvent?: "-"
        val timeChange = matchs.strTime?: "-"
        nameHomeTeam.text = matchs.strHomeTeam ?: "-"
        nameAwayTeam.text = matchs.strAwayTeam ?: "-"
        scoreHomeTeam.text = matchs.intHomeScore ?: "-"
        scoreAwayTeam.text = matchs.intAwayScore ?: "-"
        if(dateChange!= "-" && timeChange != "-"){
            dateEvent.text = changeDateFormat(dateChange, timeChange)
        }else{
            dateEvent.text = "-"
        }
        itemView.setOnClickListener { listener(matchs) }
    }
}