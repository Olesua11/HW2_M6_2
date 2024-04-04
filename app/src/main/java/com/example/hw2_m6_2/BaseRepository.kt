package com.example.hw2_m6_2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hw2_m6_2.di.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

object BaseRepository {
    fun <T> performNetworkRequest(apiCall: suspend () -> Response<T>): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!!))
                } else {
                    emit(Resource.Error("Unsuccessful response: ${response.code()}"))
                }
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: "Unknown error!"
                emit(Resource.Error(errorMessage))
                Log.e("ololo", errorMessage)
            }
        }
}
