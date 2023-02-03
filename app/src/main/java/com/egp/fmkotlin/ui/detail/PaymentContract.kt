package com.egp.fmkotlin.ui.detail

import com.egp.fmkotlin.base.BasePresenter
import com.egp.fmkotlin.base.BaseView
import com.egp.fmkotlin.model.response.checkout.CheckoutResponse

interface PaymentContract {

    interface View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message:String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(foodId:String, userId: String, quantity: String, total: String, view: android.view.View)
    }
}