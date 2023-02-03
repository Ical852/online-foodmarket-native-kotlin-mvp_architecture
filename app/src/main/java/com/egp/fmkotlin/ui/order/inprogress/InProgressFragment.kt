package com.egp.fmkotlin.ui.order.inprogress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.dummy.ProfileModel
import com.egp.fmkotlin.model.response.transaction.Data
import com.egp.fmkotlin.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_in_progress.*
import kotlinx.android.synthetic.main.fragment_profile_account.*

class InProgressFragment : Fragment(), InProgressAdapter.ItemAdapterCallback {

    private var adapter : InProgressAdapter? = null
    var inProgressList:ArrayList<Data>? = ArrayList()

    private var menuArrayList : ArrayList<ProfileModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_in_progress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        inProgressList = arguments?.getParcelableArrayList("data")

        if (!inProgressList.isNullOrEmpty()) {
            adapter = InProgressAdapter(inProgressList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rvInProgress.adapter = adapter
            rvInProgress.layoutManager = layoutManager
        }
    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity, ""+data.food.name, Toast.LENGTH_SHORT).show()
    }
}