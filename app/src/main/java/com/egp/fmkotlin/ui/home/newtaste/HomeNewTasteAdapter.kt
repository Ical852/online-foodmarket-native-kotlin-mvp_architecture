package com.egp.fmkotlin.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.response.home.Data
import com.egp.fmkotlin.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class HomeNewTasteAdapter(
        private val listData :List<Data>,
        private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>() {

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(data : Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.name
                tvPrice.formatPrice(data.price.toString())
                rbFood.rating = data.rate!!.toFloat()
                tvRatingText.text = data.rate.toString()

                Glide.with(context)
                        .load(data.picturePath)
                        .into(ivPoster)

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewTasteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewTasteAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}