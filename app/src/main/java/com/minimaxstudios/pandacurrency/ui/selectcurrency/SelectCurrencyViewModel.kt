package com.minimaxstudios.pandacurrency.ui.selectcurrency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectCurrencyViewModel: ViewModel() {
    private val _currencies = MutableLiveData<ArrayList<String>>()
    val currencies: LiveData<ArrayList<String>>
        get() = _currencies

    private val _selectedCurrency = MutableLiveData<String>()
    val selectedCurrency: LiveData<String>
        get() = _selectedCurrency

}