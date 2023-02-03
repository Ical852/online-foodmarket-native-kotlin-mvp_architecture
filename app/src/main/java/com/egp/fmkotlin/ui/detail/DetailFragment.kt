package com.egp.fmkotlin.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.response.home.Data
import com.egp.fmkotlin.utils.Helpers.formatPrice
import com.egp.fmkotlin.utils.Helpers.getCurrencyIDR
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.tvTitle
import kotlinx.android.synthetic.main.item_home_horizontal.*


class DetailFragment : Fragment() {

    var bundle: Bundle ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }

        btnBackDetail.setOnClickListener {
            (activity as DetailActivity).onBackPressed()
        }

        btnOrderNow.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_payment, bundle)
        }
    }

    private fun initView(data: Data?) {
        bundle = bundleOf("data" to data)

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(ivDetailPicture)

        tvTitle.text = data?.name
        tvDesc.text = data?.description
        rbText.text = data?.rate.toString()
        ratingBar.rating = data?.rate?.toFloat() ?: 0f
        tvIngredients.text = data?.ingredients
        tvPrice.text = data?.price?.toDouble()?.let { getCurrencyIDR(it) }
    }

}