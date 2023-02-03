package com.egp.fmkotlin.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.egp.fmkotlin.FoodMarket
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.request.RegisterRequest
import com.egp.fmkotlin.model.response.login.LoginResponse
import com.egp.fmkotlin.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_up_address.*

class SignUpAddressFragment : Fragment(), SignUpContract.View {

    private lateinit var data : RegisterRequest
    lateinit var presenter : SignUpPresenter
    var progressDialog: Dialog? =  null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this)

        data = arguments?.getParcelable<RegisterRequest>("data")!!
        initDummy()
        initListener()
        initView()

        (activity as AuthActivity).toolbarSignUpAddress()
    }

    private fun initDummy() {
        etPhoneNumber.setText("085231236947")
        etAddress.setText("Poris Indah")
        etCity.setText("Jakarta")
        etHouseNumber.setText("14185")
    }

    private fun initListener() {
        btnSignUpNow.setOnClickListener {

            var phone = etPhoneNumber.text.toString()
            var address = etAddress.text.toString()
            var city = etCity.text.toString()
            var house = etHouseNumber.text.toString()

            data.let {
                it.address = address
                it.phoneNumber = phone
                it.city = city
                it.houseNumber = house
            }

            if (phone.isNullOrEmpty()) {
                etPhoneNumber.error = "Isi Nomor Telepon Terlebih Dahulu"
                etPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()) {
                etAddress.error = "Isi Alamat Terlebih Dahulu"
                etAddress.requestFocus()
            } else if (city.isNullOrEmpty()) {
                etCity.error = "Isi Kota Terlebih Dahulu"
                etCity.requestFocus()
            } else if (house.isNullOrEmpty()) {
                etHouseNumber.error = "Isi Nomor Rumah Terlebih Dahulu"
                etHouseNumber.requestFocus()
            } else {
                presenter.submitRegister(data, it)
                if (data.filePath != null) {
                    presenter.submitPhotoRegister(data.filePath!!, it)
                }
            }

        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.instance.setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)

        FoodMarket.instance.setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                    .navigate(R.id.action_sign_up_success, null)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
                .navigate(R.id.action_sign_up_success, null)
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, "Gagal : "+message, Toast.LENGTH_SHORT).show()
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