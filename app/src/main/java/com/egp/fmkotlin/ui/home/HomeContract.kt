package com.egp.fmkotlin.ui.home

import com.egp.fmkotlin.base.BasePresenter
import com.egp.fmkotlin.base.BaseView
import com.egp.fmkotlin.model.response.home.HomeResponse
import com.egp.fmkotlin.model.response.login.LoginResponse

interface HomeContract {

    interface View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message:String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}