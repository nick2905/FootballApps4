package com.dicoding.nicolas.model

data class FavoriteModelNext(
    val id: Long?,
    val idEvent: String?,
    val idLeague: String?,
    val strLeague: String?,
    val strSport: String?,
    val dateEvent: String?,
    val strTime: String?,
    //Home
    val idHomeTeam: String?,
    val strHomeGoalDetails: String?,
    val intHomeScore: String?,
    val strHomeTeam: String?,
    //Away
    val idAwayTeam: String?,
    val strAwayGoalDetails: String?,
    val intAwayScore: String?,
    val strAwayTeam: String?
){
    companion object{
        const val TABLE_FAV_NEXT: String = "TABLE_FAV_NEXT"
        val ID: String = "ID"
        val ID_EVENT: String = "ID_EVENT"
        val ID_LEAGUE: String = "ID_LEAGUE"
        val STR_LEAGUE: String = "STR_LEAGUE"
        val STR_SPORT: String = "STR_SPORT"
        val DATE_EVENT: String = "DATE_EVENT"
        val STR_TIME: String = "STR_TIME"
        val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        val STR_HOME_DETAIL: String = "STR_HOME_DETAIL"
        val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        val STR_AWAY_DETAIL: String = "STR_AWAY_DETAIL"
        val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
    }
}

data class FavoriteModelLast(
    val id: Long?,
    val idEvent: String?,
    val idLeague: String?,
    val strLeague: String?,
    val strSport: String?,
    val dateEvent: String?,
    val strTime: String?,
    //Home
    val idHomeTeam: String?,
    val strHomeGoalDetails: String?,
    val intHomeScore: String?,
    //nameHomeTeam
    val strHomeTeam: String?,
    //Away
    val idAwayTeam: String?,
    val strAwayGoalDetails: String?,
    val intAwayScore: String?,
    val strAwayTeam: String?
){
    companion object{
        const val TABLE_FAV_LAST: String = "TABLE_FAV_LAST"
        val ID: String = "ID"
        val ID_EVENT: String = "ID_EVENT"
        val ID_LEAGUE: String = "ID_LEAGUE"
        val STR_LEAGUE: String = "STR_LEAGUE"
        val STR_SPORT: String = "STR_SPORT"
        val DATE_EVENT: String = "DATE_EVENT"
        val STR_TIME: String = "STR_TIME"
        val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        val STR_HOME_DETAIL: String = "STR_HOME_DETAIL"
        val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        val STR_AWAY_DETAIL: String = "STR_AWAY_DETAIL"
        val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
    }
}