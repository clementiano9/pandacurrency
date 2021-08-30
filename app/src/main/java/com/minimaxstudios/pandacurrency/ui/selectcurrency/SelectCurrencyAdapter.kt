package com.minimaxstudios.pandacurrency.ui.selectcurrency

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minimaxstudios.pandacurrency.R
import com.minimaxstudios.pandacurrency.extension.inflate

class SelectCurrencyAdapter(val onCurrencySelected: ((String) -> Any)): RecyclerView.Adapter<SelectCurrencyAdapter.CurrencyViewHolder>() {
    // TODO: Data holder variable
    val data = arrayListOf("Nigerian Naira (NGN)", "United States Dollars (USD)", "Great Britain Pounds (GBP)", "European Euros (EU)")

    inner class CurrencyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(item: String) { // TODO: Bind to data
            val currencyText = itemView.findViewById<TextView>(R.id.currencyText)
            currencyText.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(parent.inflate(R.layout.item_currency))
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = data[position]
        holder.bindView(item)
        holder.itemView.setOnClickListener {
            Log.d(TAG, "Clicked item")
            onCurrencySelected(item)
        }
    }

    override fun getItemCount(): Int = data.size

    companion object {
        val TAG: String = SelectCurrencyAdapter::class.java.simpleName
    }
}