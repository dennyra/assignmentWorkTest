package project.indie.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {

    fun create(): PostService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://private-a8e48-hcidtest.apiary-mock.com/")
            .build()
        return retrofit.create(PostService::class.java)
    }
}