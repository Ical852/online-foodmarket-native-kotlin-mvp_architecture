package com.egp.fmkotlin.ui.auth.signup

import android.net.Uri
import com.egp.fmkotlin.base.BasePresenter
import com.egp.fmkotlin.base.BaseView
import com.egp.fmkotlin.model.request.RegisterRequest
import com.egp.fmkotlin.model.response.login.LoginResponse

interface SignUpContract {

    interface View: BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message:String)
    }

    interface Presenter : SignUpContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(filePath : Uri, view: android.view.View)
    }
}