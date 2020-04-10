package com.dicoding.nicolas.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dicoding.nicolas.R
import com.dicoding.nicolas.UI.UIDetailMatchActivity
import com.dicoding.nicolas.api.ApiRepository
import com.dicoding.nicolas.db.database
import com.dicoding.nicolas.model.FavoriteModelLast
import com.dicoding.nicolas.model.FavoriteModelNext
import com.dicoding.nicolas.model.ItemEvent
import com.dicoding.nicolas.model.TeamLogo
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        lateinit var idEvent: String
        var idHomeTeam: String? = null
        var idAwayTeam: String? = null
    }

    private lateinit var presenter: DetailMatchPresenter
    var linearLayout: LinearLayout? = null
    var imageHome: ImageView? = null
    var imageAway: ImageView? = null
    lateinit var scoreHome: TextView
    lateinit var scoreAway: TextView
    var dateEvent: TextView? = null
    var timeEvent: TextView? = null
    var nameHomeTeam: TextView? = null
    var nameAwayTeam: TextView? = null
    var nameLeague: TextView? = null
    lateinit var homeGoal: TextView
    lateinit var awayGoal: TextView
    var progressBar: ProgressBar? = null

    var menuItem: Menu? = null
    var isFavorite: Boolean = false
    var itemEvent: ItemEvent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val dataEvent = intent.getParcelableExtra<ItemEvent>(EXTRA_DETAIL)

        idHomeTeam = intent.getStringExtra("idHomeTeam")
        idAwayTeam = intent.getStringExtra("idAwayTeam")
        idEvent = intent.getStringExtra("idEvent")!!
        UIDetailMatchActivity().setContentView(this)
        linearLayout = findViewById(R.id.linearLayout)
        progressBar = findViewById(R.id.progressDetail)
        imageHome = findViewById(R.id.imageHome)
        imageAway = findViewById(R.id.imageAway)
        scoreHome = findViewById(R.id.scoreHome)
        scoreAway = findViewById(R.id.scoreAway)
        dateEvent = findViewById(R.id.dateEvent)
        timeEvent = findViewById(R.id.timeEvent)
        nameHomeTeam = findViewById(R.id.name_home_team)
        nameAwayTeam = findViewById(R.id.name_team_away)
        nameLeague = findViewById(R.id.type_league)
        homeGoal = findViewById(R.id.homeDetailScore)
        awayGoal = findViewById(R.id.awayDetailScore)
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)
        presenter.getDetailMatch(idEvent, idHomeTeam, idAwayTeam)

        favoriteNext()
        favoriteLast()
    }

    override fun showLoading() {
        progressBar?.visibility = VISIBLE
        linearLayout?.visibility = INVISIBLE
    }

    override fun hideLoading() {
        progressBar?.visibility = INVISIBLE
        linearLayout?.visibility = VISIBLE
        menuItem?.findItem(R.id.add_to_favorite)?.isVisible = true
    }

    override fun showDetailMatch(events: List<ItemEvent>?, team: List<TeamLogo>, team2: List<TeamLogo>) {
        itemEvent = events?.get(0)
        scoreHome.text = events?.get(0)?.intHomeScore
        scoreAway.text = events?.get(0)?.intAwayScore
        dateEvent?.text = events?.get(0)?.dateEvent
        timeEvent?.text = events?.get(0)?.strTime
        nameHomeTeam?.text = events?.get(0)?.strHomeTeam
        nameAwayTeam?.text = events?.get(0)?.strAwayTeam
        nameLeague?.text = events?.get(0)?.strLeague
        homeGoal.text = events?.get(0)?.strHomeGoalDetails
        awayGoal.text = events?.get(0)?.strAwayGoalDetails

        Picasso.get()
            .load(team[0].strTeamBadge)
            .into(imageHome)

        Picasso.get()
            .load(team2[0].strTeamBadge)
            .into(imageAway)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        menuItem?.findItem(R.id.add_to_favorite)?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) deleteFromFavorite()
                else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        if (itemEvent?.intHomeScore == null) {
            try {
                database.use {
                    insert(
                        FavoriteModelNext.TABLE_FAV_NEXT,
                        FavoriteModelNext.ID_EVENT to itemEvent?.idEvent,
                        FavoriteModelNext.ID_LEAGUE to itemEvent?.idLeague,
                        FavoriteModelNext.STR_LEAGUE to itemEvent?.strLeague,
                        FavoriteModelNext.STR_SPORT to itemEvent?.strSport,
                        FavoriteModelNext.DATE_EVENT to itemEvent?.dateEvent,
                        FavoriteModelNext.STR_TIME to itemEvent?.strTime,
                        FavoriteModelNext.ID_HOME_TEAM to itemEvent?.idHomeTeam,
                        FavoriteModelNext.STR_HOME_DETAIL to itemEvent?.strHomeGoalDetails,
                        FavoriteModelNext.INT_HOME_SCORE to itemEvent?.intHomeScore,
                        FavoriteModelNext.STR_HOME_TEAM to itemEvent?.strHomeTeam,
                        FavoriteModelNext.ID_AWAY_TEAM to itemEvent?.idAwayTeam,
                        FavoriteModelNext.STR_AWAY_DETAIL to itemEvent?.strAwayGoalDetails,
                        FavoriteModelNext.INT_AWAY_SCORE to itemEvent?.intAwayScore,
                        FavoriteModelNext.STR_AWAY_TEAM to itemEvent?.strAwayTeam
                    )
                }
                Toast.makeText(this, "Add to Favorite", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, "failed to add to Favorite", Toast.LENGTH_SHORT).show()
            }
        } else {
            try {
                database.use {
                    insert(
                        FavoriteModelLast.TABLE_FAV_LAST,
                        FavoriteModelLast.ID_EVENT to itemEvent?.idEvent,
                        FavoriteModelLast.ID_LEAGUE to itemEvent?.idLeague,
                        FavoriteModelLast.STR_LEAGUE to itemEvent?.strLeague,
                        FavoriteModelLast.STR_SPORT to itemEvent?.strSport,
                        FavoriteModelLast.DATE_EVENT to itemEvent?.dateEvent,
                        FavoriteModelLast.STR_TIME to itemEvent?.strTime,
                        FavoriteModelLast.ID_HOME_TEAM to itemEvent?.idHomeTeam,
                        FavoriteModelLast.STR_HOME_DETAIL to itemEvent?.strHomeGoalDetails,
                        FavoriteModelLast.INT_HOME_SCORE to itemEvent?.intHomeScore,
                        FavoriteModelLast.STR_HOME_TEAM to itemEvent?.strHomeTeam,
                        FavoriteModelLast.ID_AWAY_TEAM to itemEvent?.idAwayTeam,
                        FavoriteModelLast.STR_AWAY_DETAIL to itemEvent?.strAwayGoalDetails,
                        FavoriteModelLast.INT_AWAY_SCORE to itemEvent?.intAwayScore,
                        FavoriteModelLast.STR_AWAY_TEAM to itemEvent?.strAwayTeam
                    )
                }
                Toast.makeText(this, "Add to Favorite", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, "Failed to add to Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteFromFavorite() {
        if (itemEvent?.intHomeScore == null) {
            try {
                database.use {
                    delete(
                        FavoriteModelNext.TABLE_FAV_NEXT,
                        "(ID_EVENT = {id})",
                        "id" to idEvent
                    )
                }
                Toast.makeText(this, "Delete from favorite", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, "Failed to delete from favorite", Toast.LENGTH_SHORT).show()
            }
        } else {
            try {
                database.use {
                    delete(
                        FavoriteModelLast.TABLE_FAV_LAST,
                        "(ID_EVENT = {id})",
                        "id" to idEvent
                    )
                }
                Toast.makeText(this, "Delete from favorite", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, "Failed to delete from favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun favoriteNext() {
        database.use {
            val result = select(FavoriteModelNext.TABLE_FAV_NEXT).whereArgs(
                "(ID_EVENT = {id})",
                "id" to idEvent
            )
            val favorite = result.parseList(classParser<FavoriteModelNext>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun favoriteLast() {
        database.use {
            val result = select(FavoriteModelLast.TABLE_FAV_LAST).whereArgs(
                "(ID_EVENT = {id})",
                "id" to idEvent
            )
            val favorite = result.parseList(classParser<FavoriteModelLast>())
            if (!favorite.isEmpty()) isFavorite = true
        }

    }
}
