package com.egp.fmkotlin.ui.order.inprogress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.response.transaction.Data
import com.egp.fmkotlin.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_inprogress.view.*

class InProgressAdapter(
        private val listData :List<Data>,
        private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<InProgressAdapter.ViewHolder>() {

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(data : Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.food.name
                tvPrice.formatPrice(data.food.price.toString())

                Glide.with(context)
                        .load(data.food.picturePath)
                        .into(ivPoster)

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_inprogress, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}