package com.minimaxstudios.pandacurrency.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.minimaxstudios.pandacurrency.network.request.RequestManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val request: RequestManager): ViewModel() {
    private val _amountString = MutableLiveData<String>()
    val amountString: LiveData<String> = _amountString

//    private val _amount

    private val _currency = MutableLiveData<Int>()

    val currency: LiveData<Int> = _currency

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        Log.d("MainViewModel", "Fetching countries...")
        val subscription = request.fetchCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.success) {
                    Log.d(TAG, "Success network")
                    mapCurrencies(it.currencies)
                } else {
                    Log.d(TAG, "network failure")
                }
            }, {
                Log.w(TAG, "Error fetching currencies", it)
            })
    }

    fun mapCurrencies(currencies: JsonObject) {
        Log.d(TAG, "Mapping currencies: $currencies")
        val countryMapType: Type = object : TypeToken<Map<String?, String?>?>() {}.type
        val countryMap: Map<Int, String> = Gson().fromJson(currencies.toString(), countryMapType)
        Log.d(TAG, "Converted: $countryMap")


    }

    companion object {
        val TAG:String = MainViewModel::class.java.simpleName
    }
}