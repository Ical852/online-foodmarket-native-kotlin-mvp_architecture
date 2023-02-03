package com.egp.fmkotlin.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.egp.fmkotlin.ui.home.newtaste.HomeNewTasteFragment
import com.egp.fmkotlin.ui.home.popular.HomePopularFragment
import com.egp.fmkotlin.ui.home.recommended.HomeRecommendedFragment
import com.egp.fmkotlin.ui.profile.account.ProfileAccountFragment
import com.egp.fmkotlin.ui.profile.foodmarket.ProfileFoodmarketFragment

class SectionPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Account"
            1 -> "FoodMarket"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0 -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
            1 -> {
                fragment = ProfileFoodmarketFragment()
                return fragment
            }
            else -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
        }
    }
}