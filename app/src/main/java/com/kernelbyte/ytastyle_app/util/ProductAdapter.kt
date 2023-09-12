package com.kernelbyte.ytastyle_app.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kernelbyte.ytastyle_app.databinding.ProductRowBinding
import com.kernelbyte.ytastyle_app.model.Products


 class ProductAdapter(private val products: List<Products>) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val products: Products = products[position]
        holder.bind(products)
    }

    override fun getItemCount(): Int = products.size

    class MyViewHolder(private val itemBinding: ProductRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(products: Products) {
            itemBinding.tvNombreProduct.text = products.nameProduct.toString()
            itemBinding.tvPrecioProduct.text = products.priceBuy.toString()
            itemBinding.tvQuantity.text = products.quantity.toString()
        }
    }
}