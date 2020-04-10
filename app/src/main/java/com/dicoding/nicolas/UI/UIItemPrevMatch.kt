package com.dicoding.nicolas.UI

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.dicoding.nicolas.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class UIItemPrevMatch : AnkoComponent<Context> {
    companion object {
        const val nameHomeTeam = 1
        const val nameAwayTeam = 2
        const val scoreHomeTeam = 3
        const val scoreAwayTeam = 4
        const val dateEvent = 5
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return cardView {
            lparams(matchParent, wrapContent) {
                margin = dip(8)
                cardElevation = 8f
                radius = 4f
            }
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(8)
                lparams(matchParent, wrapContent)
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    lparams(matchParent, wrapContent) { topMargin = dip(8) }

                    textView {
                        id = dateEvent
                        text = context.getString(R.string.empty)
                        textColorResource = R.color.colorPrimaryDark
                    }.lparams(wrapContent, wrapContent) {
                        marginEnd = dip(8)
                    }
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        lparams(dip(0), wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 3f
                        }

                        textView {
                            id = nameHomeTeam
                            text = context.getString(R.string.empty)
                            gravity = Gravity.CENTER
                        }.lparams(wrapContent, wrapContent) { topMargin = dip(25) }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        lparams(dip(0), wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 1f
                        }

                        textView {
                            id = scoreHomeTeam
                            textSize = 28f
                            textResource = android.R.color.black
                            text = context.getString(R.string.empty)
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(wrapContent, wrapContent) { topMargin = dip(8) }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        lparams(dip(0), wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 1f
                        }

                        textView {
                            text = context.getString(R.string.vs)
                            textSize = 18f
                        }.lparams(wrapContent, wrapContent) { topMargin = dip(8) }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        lparams(dip(0), wrapContent) {
                            gravity = Gravity.CENTER
                            weight = 1f
                        }

                        textView {
                            id = scoreAwayTeam
                            text = context.getString(R.string.empty)
                            textSize = 28f
                            textResource = android.R.color.black
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(wrapContent, wrapContent) { topMargin = dip(8) }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        lparams(dip(0), wrapContent) { weight = 3f }

                        textView {
                            id = nameAwayTeam
                            text = context.getString(R.string.empty)
                            gravity = Gravity.CENTER
                        }.lparams(wrapContent, wrapContent) { topMargin = dip(25) }
                    }
                }
            }
        }
    }
}

