package com.minimaxstudios.pandacurrency.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.minimaxstudios.pandacurrency.R
import com.minimaxstudios.pandacurrency.databinding.ActivityMainBinding
import com.minimaxstudios.pandacurrency.ui.selectcurrency.SelectCurrencyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observeUiChanges()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.currencyBtn.setOnClickListener {
            startActivityForResult(
                Intent(this, SelectCurrencyActivity::class.java), REQUEST_SELECT_CURRENCY
            )
        }
    }

    /**
     * Reacts to user input and changes from viewModel
     */
    private fun observeUiChanges() {
        binding.viewModel?.amountString?.observe(this, Observer { amt ->
            Log.d(TAG, "Amount: $amt")
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_CURRENCY && resultCode == RESULT_OK) {
            val selectedCurrency = data?.getStringExtra(SelectCurrencyActivity.EXTRA_CURRENCY)
            Log.d(TAG, "Selected currency: $selectedCurrency")
        }
    }

    companion object {
        const val REQUEST_SELECT_CURRENCY = 22
        var TAG: String = MainActivity::class.java.simpleName
    }
}