package com.dicoding.nicolas.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.nicolas.model.FavoriteModelLast
import com.dicoding.nicolas.model.FavoriteModelNext
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteModelNext.TABLE_FAV_NEXT, true,
            FavoriteModelNext.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteModelNext.ID_EVENT to TEXT,
            FavoriteModelNext.ID_LEAGUE to TEXT,
            FavoriteModelNext.STR_LEAGUE to TEXT,
            FavoriteModelNext.STR_SPORT to TEXT,
            FavoriteModelNext.DATE_EVENT to TEXT,
            FavoriteModelNext.STR_TIME to TEXT,
            FavoriteModelNext.ID_HOME_TEAM to TEXT,
            FavoriteModelNext.STR_HOME_DETAIL to TEXT,
            FavoriteModelNext.INT_HOME_SCORE to TEXT,
            FavoriteModelNext.STR_HOME_TEAM to TEXT,
            FavoriteModelNext.ID_AWAY_TEAM to TEXT,
            FavoriteModelNext.STR_AWAY_DETAIL to TEXT,
            FavoriteModelNext.INT_AWAY_SCORE to TEXT,
            FavoriteModelNext.STR_AWAY_TEAM to TEXT
        )

        db.createTable(FavoriteModelLast.TABLE_FAV_LAST, true,
            FavoriteModelLast.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteModelLast.ID_EVENT to TEXT,
            FavoriteModelLast.ID_LEAGUE to TEXT,
            FavoriteModelLast.STR_LEAGUE to TEXT,
            FavoriteModelLast.STR_SPORT to TEXT,
            FavoriteModelLast.DATE_EVENT to TEXT,
            FavoriteModelLast.STR_TIME to TEXT,
            FavoriteModelLast.ID_HOME_TEAM to TEXT,
            FavoriteModelLast.STR_HOME_DETAIL to TEXT,
            FavoriteModelLast.INT_HOME_SCORE to TEXT,
            FavoriteModelLast.STR_HOME_TEAM to TEXT,
            FavoriteModelLast.ID_AWAY_TEAM to TEXT,
            FavoriteModelLast.STR_AWAY_DETAIL to TEXT,
            FavoriteModelLast.INT_AWAY_SCORE to TEXT,
            FavoriteModelLast.STR_AWAY_TEAM to TEXT
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteModelNext.TABLE_FAV_NEXT, true)
        db.dropTable(FavoriteModelLast.TABLE_FAV_LAST, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
