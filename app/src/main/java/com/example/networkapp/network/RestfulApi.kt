package com.example.networkapp.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestfulApi {

    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("trending/movie/week")
    fun getTrendingMovie(
        @Query("api_key") api_key: String
    ):Call<ResponseMovieTrending>

    @FormUrlEncoded
    @POST("news")
    fun postNews(
        @Field("title") title: String,
        @Field("image") image:String,
        @Field("description") description:String,
        @Field("author") author:String
    ):Call<ResponseAppNews>



}
