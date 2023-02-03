package com.egp.fmkotlin.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.egp.fmkotlin.model.response.transaction.Data
import com.egp.fmkotlin.ui.home.newtaste.HomeNewTasteFragment
import com.egp.fmkotlin.ui.home.popular.HomePopularFragment
import com.egp.fmkotlin.ui.home.recommended.HomeRecommendedFragment
import com.egp.fmkotlin.ui.order.inprogress.InProgressFragment
import com.egp.fmkotlin.ui.order.pastorders.PastOrdersFragment
import com.egp.fmkotlin.ui.profile.account.ProfileAccountFragment
import com.egp.fmkotlin.ui.profile.foodmarket.ProfileFoodmarketFragment

class SectionPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    var inProgressList : ArrayList<Data>? = ArrayList()
    var pastOrdersList : ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "In Progress"
            1 -> "Post Orders"
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
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastOrdersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastOrdersList)
                fragment.arguments = bundle
                return fragment
                return fragment
            }
            else -> {
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment
                return fragment
            }
        }
    }

    fun setData(inProgressListParams : ArrayList<Data>, pastOrdersListParams : ArrayList<Data>) {
        inProgressList = inProgressListParams
        pastOrdersList = pastOrdersListParams
    }
}