package com.kernelbyte.ytastyle_app.model

data class Products(
    val idProduct: Int,
    val quantity: Int,
    val priceBuy: Double,
    val barcode: String,
    val nameProduct: String,
    val dateProduct: String,
    val priceCost: Double,
    val idCategoryProduct: Int,
    val idStatusProduct: Int,
    val color: String,
    val productImage: String,
    val description: String
)