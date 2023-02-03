package com.egp.fmkotlin.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.egp.fmkotlin.FoodMarket
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.response.checkout.CheckoutResponse
import com.egp.fmkotlin.model.response.home.Data
import com.egp.fmkotlin.model.response.login.User
import com.egp.fmkotlin.utils.Helpers
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.android.synthetic.main.fragment_payment.tvTitle

class PaymentFragment : Fragment(), PaymentContract.View {

    var progressDialog : Dialog? = null
    lateinit var presenter: PaymentPresenter
    var total : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var data = arguments?.getParcelable<Data>("data")
        initView(data)
        initView()
        presenter = PaymentPresenter(this)

        (activity as DetailActivity).toolbarPayment()

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

    private fun initView(data: Data?) {
        tvTitle.text = data?.name
        tvTitlePrice.text = data?.price?.toDouble()?.let { Helpers.getCurrencyIDR(it) }

        Glide.with(requireContext())
                .load(data?.picturePath)
                .into(ivPoster)

        tvItemName.text = data?.name
        tvItemPrice.text = data?.price?.toDouble()?.let { Helpers.getCurrencyIDR(it) }

        if (!data?.price.toString().isNullOrEmpty()) {
            var totalTax = data?.price?.div(10)
            tvTax.text = totalTax?.toDouble()?.let { Helpers.getCurrencyIDR(it) }

            total = data?.price!! + totalTax!! + 50000
            tvTotalPrice.text = total?.toDouble()?.let { Helpers.getCurrencyIDR(it) }
        } else {
            tvPrice.text = "IDR. 0"
            tvTax.text = "IDR. 0"
            tvTotalPrice.text = "IDR. 0"
        }

        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        tvCustName.text = userResponse.name
        tvCustPhonNum.text = userResponse.phoneNumber.toString()
        tvCustAddress.text = userResponse.address.toString()
        tvCustHouseNum.text = userResponse.houseNumber.toString()
        tvCustCity.text = userResponse.city.toString()

        btnCheckoutNow.setOnClickListener {
            presenter.getCheckout(
                    data?.id.toString(),
                    userResponse.id.toString(),
                    "1",
                    total.toString(),
                    it
            )

        }
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        val i =  Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)

        Navigation.findNavController(view)
                .navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context, "Gagal : "+message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}