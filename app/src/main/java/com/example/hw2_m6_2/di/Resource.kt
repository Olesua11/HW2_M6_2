package com.example.hw2_m6_2.di

sealed class Resource<T>(
    val data: T? = null,
    val massage: String? = null
) {
    class Loading<T>: Resource<T>()
    class Success<T>(data: T): Resource<T>(data = data)
    class Error<T>(massage: String): Resource<T>(massage = massage)
}