package com.egp.fmkotlin.ui.order.pastorders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.response.transaction.Data
import com.egp.fmkotlin.utils.Helpers.convertLongtoTime
import com.egp.fmkotlin.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_pastorders.view.*

class PastOrdersAdapter(
        private val listData :List<Data>,
        private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<PastOrdersAdapter.ViewHolder>() {

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(data : Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.food.name
                tvPrice.formatPrice(data.food.price.toString())
                tvDate.text = data.food.createdAt.convertLongtoTime("MM dd, HH.mm")

                Glide.with(context)
                        .load(data.food.picturePath)
                        .into(ivPoster)

                if (data.status.equals("CANCELLED", true)) {
                    tvStatus.visibility = View.VISIBLE
                }

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorders, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}