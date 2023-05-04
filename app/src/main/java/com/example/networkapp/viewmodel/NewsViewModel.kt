package com.example.networkapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkapp.network.ResponseAppNews
import com.example.networkapp.network.ResponseDataNewsItem
import com.example.networkapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    lateinit var liveDataNews : MutableLiveData<List<ResponseDataNewsItem>>
    lateinit var postDataNews: MutableLiveData<ResponseAppNews>

    init {
        liveDataNews = MutableLiveData()
        postDataNews = MutableLiveData()
    }

    fun postApiNews(title: String,image:String,description:String,author:String){
        RetrofitClient.instance.postNews(title,image,description,author)
            .enqueue(object :Callback<ResponseAppNews>{
                override fun onResponse(
                    call: Call<ResponseAppNews>,
                    response: Response<ResponseAppNews>
                ) {
                    if (response.isSuccessful){
                        postDataNews.postValue(response.body())
                    } else {
                        postDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseAppNews>, t: Throwable) {
                    postDataNews.postValue(null)
                }


            })


    }

    fun callApiNews(){
        RetrofitClient.instance.getAllNews()
            .enqueue(object : Callback<List<ResponseDataNewsItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataNewsItem>>,
                    response: Response<List<ResponseDataNewsItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataNews.postValue(response.body())
                    } else {
                        liveDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                    liveDataNews.postValue(null)
                }

            })
    }

}