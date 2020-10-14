package com.example.myguysapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.my_guys_item.*

class MyGuysAdapter(private val context: Context, private val items: ArrayList<MyGuys>):
    RecyclerView.Adapter<MyGuysAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_guys_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: MyGuys){
            txtGuysName.text = item.nama
            txtGuysEmail.text = item.email
            txtGuysTelp.text = item.telp
        }
    }

}