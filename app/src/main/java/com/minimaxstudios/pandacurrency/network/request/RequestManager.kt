package com.minimaxstudios.pandacurrency.network.request

import com.minimaxstudios.pandacurrency.data.BaseResponse
import com.minimaxstudios.pandacurrency.data.CountriesResponse
import com.minimaxstudios.pandacurrency.network.ApiInterface
import io.reactivex.Observable
import javax.inject.Inject

class RequestManager @Inject constructor(val api: ApiInterface) {
    fun fetchCountries(): Observable<CountriesResponse> {
        return api.fetchCurrencyList()
    }
}