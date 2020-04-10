package com.dicoding.nicolas.UI

import android.content.Context
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*

class UIItemLiga : AnkoComponent<Context> {
    companion object {
        const val imgItem = 1
        const val nameItem = 2
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return verticalLayout {
            padding = dip(16)
            imageView {
                id = imgItem
            }.lparams(width = dip(50), height = dip(50))
            textView {
                id = nameItem
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.CENTER_VERTICAL
                margin = dip(16)
            }
        }
    }
}