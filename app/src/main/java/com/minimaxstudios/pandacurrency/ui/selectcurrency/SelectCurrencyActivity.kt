package com.minimaxstudios.pandacurrency.ui.selectcurrency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.minimaxstudios.pandacurrency.R
import com.minimaxstudios.pandacurrency.databinding.ActivitySelectCurrencyBinding
import com.minimaxstudios.pandacurrency.ui.main.MainActivity

class SelectCurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectCurrencyBinding
    private lateinit var viewModel: SelectCurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_currency)
        viewModel = ViewModelProvider(this).get(SelectCurrencyViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = getString(R.string.select_currency)
            }
        }

        observeUiChanges()
        setupRecyclerView()
    }

    private fun observeUiChanges() {

    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = SelectCurrencyAdapter(onCurrencySelected = onCurrencyItemSelected)
            layoutManager = LinearLayoutManager(this@SelectCurrencyActivity)
        }
    }

    private val onCurrencyItemSelected =  { currency: String ->
        Log.d(TAG, "Putting $currency into result")
        val intent = Intent()
        intent.putExtra(EXTRA_CURRENCY, currency)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        val TAG: String = SelectCurrencyActivity::class.java.simpleName
        const val EXTRA_CURRENCY = "EXTRA_CURRENCY"
    }
}