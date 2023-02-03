package com.egp.fmkotlin.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.response.transaction.Data
import com.egp.fmkotlin.model.response.transaction.TransactionResponse
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class OrderFragment : Fragment(), OrderContract.View {

    lateinit var presenter: OrderPresenter
    var progressDialog : Dialog? = null

    var inProgressList: ArrayList<Data>? = ArrayList()
    var pastOrdersList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()) {
            ll_empty.visibility = View.VISIBLE
            ll_tab.visibility = View.GONE
            include_toolbar.visibility = View.GONE
        } else {
            ll_empty.visibility = View.GONE
            ll_tab.visibility = View.VISIBLE
            include_toolbar.visibility = View.VISIBLE
            toolbar.title = "Your Orders"
            toolbar.subtitle = "Wait for best meal"

            for (a in transactionResponse.data.indices) {
                if (
                        transactionResponse.data[a].status.equals("ON_DELIVERY", true) ||
                        transactionResponse.data[a].status.equals("DELIVERED", true)    ) {
                    inProgressList?.add(transactionResponse.data[a])
                } else if (
                        transactionResponse.data[a].status.equals("PENDING", true) ||
                        transactionResponse.data[a].status.equals("CANCELLED", true) ||
                        transactionResponse.data[a].status.equals("SUCCESS", true)) {
                    pastOrdersList?.add(transactionResponse.data[a])
                }
            }

            val sectionPagerAdapter = SectionPagerAdapter(
                    childFragmentManager
            )
            sectionPagerAdapter.setData(inProgressList!!, pastOrdersList!!)
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(context, "Gagal : "+message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}