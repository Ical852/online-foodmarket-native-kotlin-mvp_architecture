package com.egp.fmkotlin.ui.auth.signup

import android.app.Activity
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
import com.bumptech.glide.request.RequestOptions
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.request.RegisterRequest
import com.egp.fmkotlin.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    var filePath : Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummy()
        initListener()

        (activity as AuthActivity).toolbarSignUp()
    }

    private fun initListener() {
        ivProfile.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        btnContinue.setOnClickListener {

            var fullName = etFullName.text.toString()
            var email = etEmail.text.toString()
            var pass = etPassword.text.toString()

            if (fullName.isNullOrEmpty()) {
                etFullName.error = "Isi Full Name Terlebih Dahulu"
                etFullName.requestFocus()
            } else if (email.isNullOrEmpty()) {
                etEmail.error = "Isi Email Terlebih Dahulu"
                etEmail.requestFocus()
            } else if (pass.isNullOrEmpty()) {
                etPassword.error = "Isi Password Terlebih Dahulu"
                etPassword.requestFocus()
            } else {
                var data = RegisterRequest(
                    fullName,
                    email,
                    pass,
                    pass,
                    "",
                    "",
                    "",
                    "",
                    filePath
                )

                var bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_sign_up_address, bundle)
            }
        }
    }

    private fun initDummy() {
        etFullName.setText("End Game Programmer")
        etEmail.setText("egp@gmail.com")
        etPassword.setText("12345678")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(activity, ""+ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}