package com.dicoding.nicolas.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.nicolas.R
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.model.ItemLeague
import com.dicoding.nicolas.model.ItemLiga
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.favorite_activity.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_activity)
        viewpager_favorite.adapter = PagerCustomAdapter(supportFragmentManager)
        tabs_favorite.setupWithViewPager(viewpager_favorite)
    }
}