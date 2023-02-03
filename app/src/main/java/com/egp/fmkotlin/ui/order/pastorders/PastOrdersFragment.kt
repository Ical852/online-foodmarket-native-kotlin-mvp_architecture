package com.egp.fmkotlin.ui.order.pastorders

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
import com.egp.fmkotlin.ui.order.inprogress.InProgressAdapter
import kotlinx.android.synthetic.main.fragment_past_orders.*

class PastOrdersFragment : Fragment(), PastOrdersAdapter.ItemAdapterCallback {

    private var adapter : PastOrdersAdapter? = null
    var pastOrdersList:ArrayList<Data>? = ArrayList()

    private var menuArrayList : ArrayList<ProfileModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_past_orders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastOrdersList = arguments?.getParcelableArrayList("data")

        if (!pastOrdersList.isNullOrEmpty()) {
            adapter = PastOrdersAdapter(pastOrdersList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rvPastOrders.adapter = adapter
            rvPastOrders.layoutManager = layoutManager
        }
    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity, ""+data.food.name, Toast.LENGTH_SHORT).show()
    }
}