package com.example.networkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.networkapp.databinding.ActivityAddNewsActvityBinding
import com.example.networkapp.viewmodel.NewsViewModel

class AddNewsActvity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewsActvityBinding
    private val viewModel:NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val description = binding.edtDesc.text.toString()
            val url = binding.edtImage.text.toString()
            val auth = binding.edtAuth.text.toString()

            viewModel.postApiNews(title,url,description,auth)
            viewModel.postDataNews.observe(this){ news ->
                if (news != null){
                    Toast.makeText(this,"Berhasil menambhakan data",Toast.LENGTH_SHORT).show()
                    news.createdAt.let {
                        Log.d("Add news Activity", "onCreate: $it")
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            }
        }
    }