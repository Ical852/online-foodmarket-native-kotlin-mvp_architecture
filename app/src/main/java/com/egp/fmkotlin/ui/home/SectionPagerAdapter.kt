package com.egp.fmkotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.egp.fmkotlin.model.response.home.Data
import com.egp.fmkotlin.ui.home.newtaste.HomeNewTasteFragment
import com.egp.fmkotlin.ui.home.popular.HomePopularFragment
import com.egp.fmkotlin.ui.home.recommended.HomeRecommendedFragment

class SectionPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    var newTasteList:ArrayList<Data>? = ArrayList()
    var recommendedList:ArrayList<Data>? = ArrayList()
    var popularList:ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "New Taste"
            1 -> "Popular"
            2 -> "Recommended"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", popularList)
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = HomeRecommendedFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", recommendedList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(
            newTasteListParams : ArrayList<Data>,
            recommendedListParams : ArrayList<Data>,
            popularListParams : ArrayList<Data>) {

        newTasteList = newTasteListParams
        recommendedList = recommendedListParams
        popularList = popularListParams
    }
}