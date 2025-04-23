package com.example.calculatortipx

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatortipx.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tipOptions = resources.getStringArray(R.array.tip_options)

        val adapter = object : ArrayAdapter<String>(
            this,
            R.layout.spinner_item_with_icon,
            R.id.text1,
            tipOptions
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.spinner_item_with_icon, parent, false)
                val label = view.findViewById<TextView>(R.id.label)
                val text = view.findViewById<TextView>(R.id.text1)
                label.text = "Tip Percentage"
                text.text = tipOptions[position]
                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.spinner_dropdown_item_with_icon, parent, false)
                val text = view.findViewById<TextView>(R.id.text1)
                text.text = tipOptions[position]
                return view
            }
        }
        binding.tipOptionsSpinner.adapter = adapter
        binding.tipOptionsSpinner.setSelection(0)
        calculateTip() // panggil saat awal agar 15% dihitung
        binding.serviceCostInput.addTextChangedListener(tipWatcher)
        binding.tipOptionsSpinner.onItemSelectedListener = spinnerListener
        binding.roundUpSwitch.setOnCheckedChangeListener { _, _ -> calculateTip() }
    }

    private val tipWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = calculateTip()
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private val spinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            calculateTip()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private fun calculateTip() {
        val cost = binding.serviceCostInput.text.toString().toDoubleOrNull() ?: 0.0

        val tipPercent = binding.tipOptionsSpinner.selectedItem.toString().removeSuffix("%").toDoubleOrNull() ?: 0.0
        var tip = cost * tipPercent / 100
        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        binding.tipResult.text = "Tip Amount: $formattedTip"
    }
}