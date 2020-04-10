package com.dicoding.nicolas.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.nicolas.R
import com.dicoding.nicolas.UI.UISearchActivity
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.detailMatch.DetailMatchActivity
import com.dicoding.nicolas.model.ItemEvent
import com.google.gson.Gson
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class SearchEventActivity : AppCompatActivity(), SearchMatchView {
    private var matchs: MutableList<ItemEvent> = mutableListOf()
    private lateinit var adapter: SearchAdapter
    private lateinit var presenter: SearchPresenter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBarSearch: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UISearchActivity().setContentView(this)
        progressBarSearch = findViewById(R.id.progressBarSearch)
        listMatch = findViewById(R.id.rvSearch)
        adapter = SearchAdapter(matchs, { itemEvent: ItemEvent ->
            onClick(itemEvent)
        })
        listMatch.adapter = adapter
        listMatch.setHasFixedSize(true)
        listMatch.layoutManager = LinearLayoutManager(this)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)
        presenter.getEvent("A")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search = menu?.findItem(R.id.action_search)?.actionView as SearchView
        search.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(data: String?): Boolean {
                Log.i("tag", "Ini adalah onQueryTextSubmit")
                if (data != null) {
                    Log.i("tag", data)
                    searchEvent(data)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun showLoading() {
        progressBarSearch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarSearch.visibility = View.INVISIBLE
    }

    override fun showSearchMatch(data: List<ItemEvent>) {
        matchs.clear()
        matchs.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun searchEvent(query: String) {
        Log.d("tag", "Search Event$query")
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this,  request, gson)
        presenter.getEvent(query)
    }

    private fun onClick(event: ItemEvent) {
        val intent = Intent(this, DetailMatchActivity::class.java)
        intent.putExtra("idEvent", event.idEvent)
        intent.putExtra("idHomeTeam", event.idHomeTeam)
        intent.putExtra("idAwayTeam", event.idAwayTeam)
        startActivity(intent)
    }
}
