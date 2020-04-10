package com.dicoding.nicolas.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.nicolas.R
import com.dicoding.nicolas.UI.UIHomeActivity
import com.dicoding.nicolas.favorite.FavoriteActivity
import com.dicoding.nicolas.model.ItemLiga
import com.dicoding.nicolas.search.SearchEventActivity
import org.jetbrains.anko.setContentView

class HomeActivity : AppCompatActivity() {
    private var items: MutableList<ItemLiga> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        UIHomeActivity(items).setContentView(this)
    }

    private fun initData() {
        val id = resources.getStringArray(R.array.data_id)
        val name = resources.getStringArray(R.array.data_name)
        val image = resources.obtainTypedArray(R.array.data_image)
        val desc = resources.getStringArray(R.array.data_description)
        items.clear()
        for (i in name.indices) {
            items.add(
                ItemLiga(
                    name[i],
                    id[i],
                    desc[i],
                    image.getResourceId(i, 0)
                )
            )
        }
        image.recycle()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_intent_search -> {
                val intent = Intent(this@HomeActivity, SearchEventActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_intent_favorite ->{
                val intent = Intent(this@HomeActivity, FavoriteActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
