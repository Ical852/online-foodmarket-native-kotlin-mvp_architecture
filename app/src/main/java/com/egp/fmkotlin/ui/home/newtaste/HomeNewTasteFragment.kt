package com.egp.fmkotlin.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.dummy.HomeVerticalModel
import com.egp.fmkotlin.model.response.home.Data
import com.egp.fmkotlin.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home_new_taste.*

class HomeNewTasteFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

//    private var foodList = ArrayList<HomeVerticalModel>()
    private var newTasteList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newTasteList = arguments?.getParcelableArrayList("data")

//        initDataDummmy()
        var adapter = HomeNewTasteAdapter(newTasteList!!, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rvHomeNewTaste.layoutManager = layoutManager
        rvHomeNewTaste.adapter = adapter


    }

//    fun initDataDummmy() {
//        foodList = ArrayList()
//        foodList.add(HomeVerticalModel("Cheery Healthy", "289000", "", 5f))
//        foodList.add(HomeVerticalModel("Burger Tamayo", "4509000","", 4f))
//        foodList.add(HomeVerticalModel("Bakwan Cihuy", "999000","", 4.5f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}