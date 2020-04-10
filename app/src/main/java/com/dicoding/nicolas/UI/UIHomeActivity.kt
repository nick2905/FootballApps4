package com.dicoding.nicolas.UI

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.nicolas.R
import com.dicoding.nicolas.detail.DetailActivity
import com.dicoding.nicolas.home.HomeActivity
import com.dicoding.nicolas.home.HomeAdapter
import com.dicoding.nicolas.model.ItemLiga
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class UIHomeActivity(val items: List<ItemLiga>) : AnkoComponent<HomeActivity> {
    override fun createView(ui: AnkoContext<HomeActivity>): View = with(ui) {
        verticalLayout {
            padding = dip(16)
            recyclerView {
                id = R.id.rvHomeMain
                layoutManager = GridLayoutManager(context, 2)
                adapter = HomeAdapter(items) {
                    startActivity<DetailActivity>(
                        DetailActivity.EXTRA_LEAGUE to it
                    )
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}