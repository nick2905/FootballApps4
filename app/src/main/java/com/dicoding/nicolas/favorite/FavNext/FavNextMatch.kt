package com.dicoding.nicolas.favorite.FavNext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.nicolas.R
import com.dicoding.nicolas.db.database
import com.dicoding.nicolas.detailMatch.DetailMatchActivity
import com.dicoding.nicolas.model.FavoriteModelNext
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavNextMatch : Fragment(), AnkoComponent<Context> {
    private var matches: MutableList<FavoriteModelNext> = mutableListOf()
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: FavNextAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = createView(AnkoContext.create(requireContext()))
        progressBar = view.findViewById(R.id.progressBarNext)
        listMatch = view.findViewById(R.id.rvNext)
        adapter = FavNextAdapter(matches, { itemEvent: FavoriteModelNext ->
            onClick(itemEvent)
        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getListMatches()
        showFavoriteData()
    }

    override fun onResume() {
        super.onResume()
        showFavoriteData()
    }

    private fun onClick(event: FavoriteModelNext) {
        val intent = Intent(activity, DetailMatchActivity::class.java)
            intent.putExtra("idEvent", event.idEvent)
            intent.putExtra("idHomeTeam", event.idHomeTeam)
            intent.putExtra("idAwayTeam", event.idAwayTeam)
            startActivity(intent)
        }

        private fun getListMatches() {
            adapter = FavNextAdapter(matches, { itemEvent: FavoriteModelNext -> onClick(itemEvent) })
            listMatch.adapter = adapter
            listMatch.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
            listMatch.layoutManager = layoutManager
        }

        private fun showFavoriteData() {
            matches.clear()
            context?.database?.use {
                progressBar.visibility = View.INVISIBLE
                val result = select(FavoriteModelNext.TABLE_FAV_NEXT)
                val favorite = result.parseList(classParser<FavoriteModelNext>())
                matches.addAll(favorite)
                adapter.notifyDataSetChanged()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    recyclerView {
                        id = R.id.rvNext
                        lparams(width = matchParent, height = wrapContent)
                    }
                    progressBar {
                        id = R.id.progressBarNext
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }
}