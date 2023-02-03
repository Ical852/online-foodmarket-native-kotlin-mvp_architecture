package com.egp.fmkotlin.ui.auth.signin

import com.egp.fmkotlin.base.BasePresenter
import com.egp.fmkotlin.base.BaseView
import com.egp.fmkotlin.model.response.login.LoginResponse

interface SignInContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message:String)
    }

    interface Presenter : SignInContract, BasePresenter {
        fun submitLogin(email : String, password : String)
    }
}