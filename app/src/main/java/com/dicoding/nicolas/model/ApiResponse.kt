package com.dicoding.nicolas.model

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class EventsResponse(
    val events: List<ItemEvent>
)

data class EventResponse(
    val event: List<ItemEvent>
)

data class AwayResponse(
    val teams: List<TeamLogo>
)

data class HomeResponse(
    val teams: List<TeamLogo>
)

data class LeagueResponse(
    val leagues: List<ItemLeague>
)

@Parcelize
data class ItemEvent(
    //Event
    var idEvent: String?,
    var idLeague: String?,
    var strLeague: String?,
    var strSport: String?,
    var dateEvent: String?,
    var strTime: String?,
    //Home
    var idHomeTeam: String?,
    var strHomeGoalDetails: String?,
    var intHomeScore: String?,
    //nameHomeTeam
    var strHomeTeam: String?,
    //Away
    var idAwayTeam: String?,
    var strAwayGoalDetails: String?,
    var intAwayScore: String?,
    var strAwayTeam: String?
) : Parcelable, Serializable

@Parcelize
data class TeamLogo(
    var idTeam: String?,
    var strTeamBadge: String?
) : Parcelable, Serializable

@Parcelize
data class ItemLiga(
    val nama: String?,
    val id: String?,
    val desc: String?,
    val image: Int?
) : Parcelable

@Parcelize
data class ItemLeague(
    var idLeague: String?,
    var strLeague: String?,
    var strDescriptionEN: String?,
    var strBadge: String?
) : Parcelable, Serializable