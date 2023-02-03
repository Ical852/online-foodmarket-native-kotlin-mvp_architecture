package com.egp.fmkotlin.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egp.fmkotlin.R
import kotlinx.android.synthetic.main.fragment_payment_success.*


class PaymentSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        btnOtherFoods.setOnClickListener {
            requireActivity().finish()
        }

        btnViewOrder.setOnClickListener {
            requireActivity().finish()
        }
    }
}