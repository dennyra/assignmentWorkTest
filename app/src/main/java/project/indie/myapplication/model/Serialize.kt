package project.indie.myapplication.model

import com.google.gson.annotations.SerializedName

data class Serialize(
    @SerializedName("data")
    var dataModel: List<DataModel>
)