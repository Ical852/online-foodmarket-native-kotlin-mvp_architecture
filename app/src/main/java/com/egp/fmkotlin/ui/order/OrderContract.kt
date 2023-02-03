package com.egp.fmkotlin.ui.order

import com.egp.fmkotlin.base.BasePresenter
import com.egp.fmkotlin.base.BaseView
import com.egp.fmkotlin.model.response.home.HomeResponse
import com.egp.fmkotlin.model.response.login.LoginResponse
import com.egp.fmkotlin.model.response.transaction.TransactionResponse

interface OrderContract {

    interface View: BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message:String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }
}