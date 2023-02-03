package com.egp.fmkotlin.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.egp.fmkotlin.R
import com.egp.fmkotlin.ui.MainActivity
import com.egp.fmkotlin.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_sign_up_address.*
import kotlinx.android.synthetic.main.fragment_sign_up_success.*

class SignUpSuccessFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnFindFoods.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finishAffinity()
        }

        (activity as AuthActivity).toolbarSignUpSucess()
    }

}