package project.indie.myapplication.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("section")
    val section:String,
    @SerializedName("items")
    val items: List<Items>
)