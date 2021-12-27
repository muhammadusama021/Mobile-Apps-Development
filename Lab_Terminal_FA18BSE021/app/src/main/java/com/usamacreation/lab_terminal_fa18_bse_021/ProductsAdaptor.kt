package com.usamacreation.lab_terminal_fa18_bse_021

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class ProductsAdaptor(val names: ArrayList<String>, val quantity: ArrayList<Int>): RecyclerView.Adapter<productViewHolder>(){
    private lateinit var  listen: onItemClickListener
    interface  onItemClickListener{
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        var view=inflater.inflate(R.layout.product_items,parent,false)
        return productViewHolder(view,listen)
    }

    override fun onBindViewHolder(holder: productViewHolder, position: Int) {
        holder.name.text=names[position]
        holder.quantity.text="Price  "+quantity[position]

    }
    override fun getItemCount(): Int {
        return names.size
    }
    fun setOnItemClickListener(listener:onItemClickListener)
    {
        listen=listener
    }
}
class productViewHolder(itemView: View, listener: ProductsAdaptor.onItemClickListener): RecyclerView.ViewHolder(itemView) {
    var name=itemView.findViewById<TextView>(R.id.name)
    var quantity=itemView.findViewById<TextView>(R.id.quantity)
    var btn=itemView.findViewById<Button>(R.id.takebtn)
    init {
        btn.setOnClickListener({
            listener.onItemClick(adapterPosition)

        })
    }

    //var img=itemView.findViewById<TextView>(R.id.profile)
}