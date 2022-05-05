package com.example.searchstationapplication.model.dto

sealed class ApiResponse<T> {
    data class Success<T>(val data: T?=null) : ApiResponse<T>()
    data class Failure<T>(val message: String="") : ApiResponse<T>()
    data class Exception<T>(val throwable: Throwable) : ApiResponse<T>()
}