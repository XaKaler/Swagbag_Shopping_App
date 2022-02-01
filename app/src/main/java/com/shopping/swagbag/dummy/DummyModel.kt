package com.shopping.swagbag.dummy

data class DummyModel(val image: String, val name: String, val details: String)

data class DummyCategoryModel(val image: Int, val name: String, val details: String, val background: Int)

data class DummyChild(val image: Int, val name: String)

data class SingleProductModel(val mainImage: String, val image1: String,  val image2: String,  val image3: String,  val image4: String,  val image5: String,
val name: String)


data class SingleProductImageModel(val mainImage: String)

data class User(val name: String, val address: String, val mobile: String, val image: String)

data class UserAddress(val address: String)

data class ProductFilter(val filterName: String)

data class CategorySideNavigationData(val address: String)

data class DummySlider(val image: String)

data class TopTrending(val image: Int)

