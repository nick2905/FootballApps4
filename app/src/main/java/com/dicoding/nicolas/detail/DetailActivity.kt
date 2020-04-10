package com.dicoding.nicolas.detail

import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.nicolas.R
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.model.ItemLeague
import com.dicoding.nicolas.model.ItemLiga
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {
    companion object {
        const val EXTRA_LEAGUE = "extra_league"
        lateinit var idLeague: String
    }

    private lateinit var presenter: DetailActivityPresenter
    var desc: TextView? = null
    var image: ImageView? = null
    var appBar: AppBarLayout? = null
    var textToolbar: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        desc = findViewById(R.id.main_descLeague)
        image = findViewById(R.id.main_backdrop)
        appBar = findViewById(R.id.appbar)
        textToolbar = findViewById(R.id.toolbar_title)

        val id = intent.getParcelableExtra<ItemLiga>(EXTRA_LEAGUE)
        idLeague = id?.id!!
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailActivityPresenter(this, request, gson)
        presenter.getDetailLeague(idLeague)
        viewpager.adapter = PagerCustomAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewpager)
    }

    override fun showDetail(leagues: List<ItemLeague>?) {
        setSupportActionBar(toolbar)
        desc?.text = leagues?.get(0)?.strDescriptionEN
        val name = leagues?.get(0)?.strLeague
        toolbar.title = ""
        textToolbar?.text = name
        Picasso.get()
            .load(leagues?.get(0)?.strBadge)
            .into(image)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
        appBar?.visibility = VISIBLE
    }
}
