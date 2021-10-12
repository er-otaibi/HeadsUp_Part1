package com.example.headsup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.celebrity_row.view.*

class CelebrityAdapter(private val myUsers: ArrayList<CelebrityDetails.CelebrityDetailsItem>):  RecyclerView.Adapter<CelebrityAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.celebrity_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list1 =myUsers[position]

        holder.itemView.apply {
            tvName.text = list1.name
            tvToboo1.text = list1.taboo1
            tvToboo2.text= list1.taboo2
            tvToboo3.text = list1.taboo3


        }
    }

    override fun getItemCount() = myUsers.size
}