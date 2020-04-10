package com.dicoding.nicolas.detail.prev

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.nicolas.UI.UIItemPrevMatch
import com.dicoding.nicolas.model.ItemEvent
import com.dicoding.nicolas.utils.changeDateFormat
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class PrevAdapter(private val matchs: List<ItemEvent>, private val listener: (ItemEvent) -> Unit) :
    RecyclerView.Adapter<PrevMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevMatchViewHolder =
        PrevMatchViewHolder(
            UIItemPrevMatch().createView(
                AnkoContext.Companion.create(parent.context)
            )
        )

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: PrevMatchViewHolder, position: Int) {
        holder.bindItem(matchs[position], listener)
    }
}

class PrevMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameHomeTeam: TextView = view.find(UIItemPrevMatch.nameHomeTeam)
    private val nameAwayTeam: TextView = view.find(UIItemPrevMatch.nameAwayTeam)
    private val scoreHomeTeam: TextView = view.find(UIItemPrevMatch.scoreHomeTeam)
    private val scoreAwayTeam: TextView = view.find(UIItemPrevMatch.scoreAwayTeam)
    private val dateEvent: TextView = view.find(UIItemPrevMatch.dateEvent)

    fun bindItem(matchs: ItemEvent, listener: (ItemEvent) -> Unit) {
        val dateChange = matchs.dateEvent!!
        val timeChange = matchs.strTime!!
        nameHomeTeam.text = matchs.strHomeTeam ?: "-"
        nameAwayTeam.text = matchs.strAwayTeam ?: "-"
        scoreHomeTeam.text = matchs.intHomeScore ?: "-"
        scoreAwayTeam.text = matchs.intAwayScore ?: "-"
        dateEvent.text = changeDateFormat(dateChange, timeChange)
        itemView.setOnClickListener { listener(matchs) }
    }
}