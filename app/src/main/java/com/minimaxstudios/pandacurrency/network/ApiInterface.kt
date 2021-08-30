package com.minimaxstudios.pandacurrency.network

import com.minimaxstudios.pandacurrency.data.BaseResponse
import com.minimaxstudios.pandacurrency.data.CountriesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("list")
    fun fetchCurrencyList(@Query("access_key")accessKey: String = "eb5c742929a1a2ad30d9e53908c232f9"): Observable<CountriesResponse>
}