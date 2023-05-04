package com.example.networkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkapp.databinding.ActivityMainBinding
import com.example.networkapp.network.ResponseDataNewsItem
import com.example.networkapp.network.ResponseMovieTrending
import com.example.networkapp.network.RetrofitClient
import com.example.networkapp.viewmodel.NewsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModelNews:NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        dataGetNews()

        //trigger crashlitic
//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(crashButton, ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT))


//      penggunaan retrofit
//        binding.btnAddNews.setOnClickListener {
//            val intent  = Intent(this,AddNewsActvity::class.java)
//            startActivity(intent)
//        }

        //penggunaan firebase auth
        binding.btnAddNews.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val apiKey = "3dc33f88c0689c50b65f6a89a2b66889"

        RetrofitClient.instance.getTrendingMovie(apiKey).enqueue(object : Callback<ResponseMovieTrending>{
            override fun onResponse(
                call: Call<ResponseMovieTrending>,
                response: Response<ResponseMovieTrending>
            ) {
               if (response.isSuccessful){
                   binding.rvList.apply {
                       layoutManager = LinearLayoutManager(
                           this@MainActivity,
                           LinearLayoutManager.VERTICAL,
                           false
                       )
                       adapter = AdapterNews(response.body()?.results ?: emptyList())
                   }
                   response.body()?.results?.map {
                       Log.d("TAG", it.originalTitle)
                   }
                   Toast.makeText(this@MainActivity,"Berhasil dipanggil", Toast.LENGTH_SHORT).show()
               } else {
                   Log.d("TAG", "onFailure: ${response.errorBody()}")
               }
            }

            override fun onFailure(call: Call<ResponseMovieTrending>, t: Throwable) {
                Log.d("TAG", "onFailure: $t ")
            }

        })

    }

//    private fun dataGetNews() {
//        viewModelNews.callApiNews()
//        viewModelNews.liveDataNews.observe(this) { news ->
//            if (news != null) {
//                //check data
//                news.map { Log.d("TAG", it.title) }
//                binding.rvList.apply {
//                    layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//                    adapter = AdapterNews(news)
//                }
//            }
//        }
//    }
}
