package com.egp.fmkotlin.ui.profile.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.dummy.ProfileModel
import com.egp.fmkotlin.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_account.*

class ProfileAccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private var menuArrayList : ArrayList<ProfileModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        var adapter = ProfileMenuAdapter(menuArrayList, this)
        var layoutManager = LinearLayoutManager(context)
        rvProfileList.layoutManager = layoutManager
        rvProfileList.adapter = adapter
    }

    fun initDataDummy() {
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileModel("Edit Profile"))
        menuArrayList.add(ProfileModel("Home Address"))
        menuArrayList.add(ProfileModel("Security"))
        menuArrayList.add(ProfileModel("Payments"))
    }

    override fun onClick(v: View, data: ProfileModel) {
        Toast.makeText(context, ""+data.title, Toast.LENGTH_SHORT).show()
    }
}