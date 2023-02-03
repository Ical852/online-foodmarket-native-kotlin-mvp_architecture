package com.egp.fmkotlin.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.egp.fmkotlin.FoodMarket
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.response.login.LoginResponse
import com.egp.fmkotlin.ui.MainActivity
import com.egp.fmkotlin.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(), SignInContract.View {

    lateinit var presenter: SignInPresenter
    var progressDialog : Dialog?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        presenter = SignInPresenter(this)

        initView()

        btnSignUp.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", 2)
            startActivity(signup)
        }

        btnSignIn.setOnClickListener {
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if (email.isNullOrEmpty()) {
                etEmail.error = "Isi Email Anda Terlebih Dahulu"
                etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                etPassword.error = "Isi Password Terlebih Dahulu"
                etPassword.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }
        }

    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.instance.setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)

        FoodMarket.instance.setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, "yah gagal : "+message, Toast.LENGTH_SHORT).show()
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

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}