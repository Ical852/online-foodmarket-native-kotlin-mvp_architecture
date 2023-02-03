package com.egp.fmkotlin.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egp.fmkotlin.R
import com.egp.fmkotlin.model.dummy.ProfileModel
import kotlinx.android.synthetic.main.item_menu_profile.view.*

class ProfileMenuAdapter(
        private val listData :List<ProfileModel>,
        private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>() {

    interface ItemAdapterCallback {
        fun onClick(v: View, data:ProfileModel)
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(data : ProfileModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.title

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_menu_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileMenuAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}