package com.dicoding.nicolas.UI

import android.view.View
import android.widget.LinearLayout
import com.dicoding.nicolas.R
import com.dicoding.nicolas.search.SearchEventActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class UISearchActivity : AnkoComponent<SearchEventActivity> {
    override fun createView(ui: AnkoContext<SearchEventActivity>): View = with(ui) {
        verticalLayout {
            padding = dip(16)
            orientation = LinearLayout.VERTICAL

            swipeRefreshLayout {
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    recyclerView {
                        id = R.id.rvSearch
                        lparams(width = matchParent, height = wrapContent)
                    }
                    progressBar {
                        id = R.id.progressBarSearch
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }
}