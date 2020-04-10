package com.dicoding.nicolas.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.nicolas.favorite.FavNext.FavNextMatch
import com.dicoding.nicolas.favorite.FavPrev.FavPrevMatch

class PagerCustomAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val pages = listOf(
        FavPrevMatch(),
        FavNextMatch()

    )

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            else -> "Next Match"
        }
    }
}