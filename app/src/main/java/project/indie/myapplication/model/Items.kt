package project.indie.myapplication.model

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("article_title")
    val article_title:String,
    @SerializedName("article_image")
    var article_image:String,
    @SerializedName("product_name")
    val product_name:String,
    @SerializedName("product_image")
    val product_image:String,
    @SerializedName("link")
    val link:String
)