package com.example.headsup

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @Headers("Content-Type: application/json")
    @POST("/celebrities/")
    fun addCelebrity(@Body celebrityData: CelebrityDetails.CelebrityDetailsItem): Call<List<CelebrityDetails.CelebrityDetailsItem>>

    @Headers("Content-Type: application/json")
    @GET("/celebrities/")
    fun getCelebrity(): Call<List<CelebrityDetails.CelebrityDetailsItem>>

    @Headers("Content-Type: application/json")
    @DELETE("/celebrities/{pk}")
    fun deleteCelebrity(@Path("pk") pk: Int ): Call<Void>

    @Headers("Content-Type: application/json")
    @PUT("/celebrities/{pk}")
    fun updateCelebrity(@Path("pk") pk : Int, @Body userData: CelebrityDetails.CelebrityDetailsItem): Call<List<CelebrityDetails.CelebrityDetailsItem>>
}