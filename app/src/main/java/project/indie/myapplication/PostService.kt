package project.indie.myapplication

import project.indie.myapplication.model.Serialize
import retrofit2.Call
import retrofit2.http.GET

interface PostService{

    @GET("home")
    fun getData(): Call<Serialize>

}