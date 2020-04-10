package com.dicoding.nicolas.UI

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.dicoding.nicolas.R
import com.dicoding.nicolas.detailMatch.DetailMatchActivity
import org.jetbrains.anko.*

class UIDetailMatchActivity : AnkoComponent<DetailMatchActivity> {

    override fun createView(ui: AnkoContext<DetailMatchActivity>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            scrollView {
                linearLayout {
                    id = R.id.linearLayout
                    orientation = LinearLayout.VERTICAL
                    visibility = View.INVISIBLE
                    linearLayout {
                        gravity = Gravity.CENTER
                        padding = dip(10)
                        imageView {
                            id = R.id.imageHome
                        }.lparams(width = dip(100), height = dip(100))
                        textView {
                            id = R.id.scoreHome
                            text = context.getString(R.string.score_home)
                        }.lparams {
                            gravity = Gravity.CENTER
                            margin = dip(10)
                            marginStart = dip(10)
                        }
                        textView {
                            text = context.getString(R.string.vs)
                        }.lparams {
                            gravity = Gravity.CENTER
                            marginStart = dip(3)
                        }
                        textView {
                            id = R.id.scoreAway
                            text = context.getString(R.string.score_away)
                        }.lparams {
                            gravity = Gravity.CENTER
                            margin = dip(10)
                        }
                        imageView {
                            id = R.id.imageAway
                        }.lparams(width = dip(100), height = dip(100)) {
                            gravity = Gravity.END
                        }
                    }.lparams(width = matchParent) {
                        gravity = Gravity.CENTER
                        topMargin = dip(5)
                    }
                    linearLayout {
                        textView {
                            text = context.getString(R.string.detail_pertandingan)
                            textColor = Color.parseColor("#070404")
                            textSize = 24f //sp
                        }
                    }.lparams(width = matchParent, height = matchParent) {
                        leftMargin = dip(16)
                        rightMargin = dip(16)
                    }
                    linearLayout {
                        gravity = Gravity.CENTER
                        padding = dip(16)
                        textView {
                            id = R.id.dateEvent
                        }
                    }.lparams(width = matchParent, height = matchParent)
//
                    linearLayout {
                        gravity = Gravity.CENTER
                        padding = dip(16)
                        textView {
                            id = R.id.timeEvent
                        }
                    }.lparams(width = matchParent, height = matchParent)

                    linearLayout {
                        gravity = Gravity.CENTER
                        padding = dip(16)
                        textView {
                            id = R.id.name_home_team
                            gravity = Gravity.LEFT
                            text = context.getString(R.string.team_home)
                            textColor = Color.BLACK
                            textSize = 18f //sp
                        }.lparams {
                            gravity = Gravity.LEFT
                        }
                        textView {
                            gravity = Gravity.CENTER
                            text = context.getString(R.string.vs)
                            textColor = Color.BLACK
                            textSize = 24f //sp
                            padding = dip(16)
                        }
                        textView {
                            id = R.id.name_team_away
                            gravity = Gravity.END
                            text = context.getString(R.string.team_away)
                            textColor = Color.BLACK
                            textSize = 18f //sp
                        }
                    }.lparams(width = matchParent, height = matchParent)

                    linearLayout {
                        gravity = Gravity.CENTER
                        padding = dip(16)
                        textView {
                            gravity = Gravity.LEFT
                            text = context.getString(R.string.league)
                            textColor = Color.BLACK
                            textSize = 24f //sp
                            padding = dip(16)
                        }.lparams {
                            gravity = Gravity.LEFT
                        }
                        textView {
                            id = R.id.type_league
                            gravity = Gravity.END
                            text = context.getString(R.string.type_league)
                            textColor = Color.BLACK
                            textSize = 18f //sp
                            padding = dip(16)
                        }
                    }.lparams(width = matchParent, height = matchParent)

                    linearLayout {
                        gravity = Gravity.CENTER
                        padding = dip(16)
                        textView {
                            text = context.getString(R.string.goal_detail)
                        }
                    }.lparams(width = matchParent, height = matchParent)
                    linearLayout {
                        gravity = Gravity.CENTER_HORIZONTAL
                        orientation = LinearLayout.HORIZONTAL
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL
                            lparams(width = 250, height = wrapContent)

                            textView {
                                id = R.id.homeDetailScore
                                textSize = 14f
                                gravity = Gravity.CENTER_HORIZONTAL
                            }
                        }

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                        }

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL
                            lparams(width = 250, height = wrapContent)
                            textView {
                                id = R.id.awayDetailScore
                                textSize = 14f
                                gravity = Gravity.CENTER_HORIZONTAL
                            }
                        }
                    }
                    //
                }.lparams(width = matchParent)
            }.lparams(width = matchParent)

            progressBar {
                id = R.id.progressDetail
            }.lparams {
                gravity = Gravity.CENTER
            }
        }
    }

}