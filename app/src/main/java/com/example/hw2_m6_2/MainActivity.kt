package com.example.hw2_m6_2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw2_m6_2.data.CartoonApiService
import com.example.hw2_m6_2.data.CharacterAdapter
import com.example.hw2_m6_2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit: Retrofit

    private lateinit var apiService: CartoonApiService
    private lateinit var characterAdapter: CharacterAdapter
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        apiService = retrofit.create(CartoonApiService::class.java)
        characterAdapter = CharacterAdapter(emptyList())

        binding.characterRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            val characters = apiService.getCharacters()
            withContext(Dispatchers.Main) {
                characterAdapter.characters = characters.results
                characterAdapter.notifyDataSetChanged()
            }
        }
    }
}