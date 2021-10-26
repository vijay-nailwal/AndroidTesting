package com.vijay.androidtesting.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.vijay.androidtesting.R
import com.vijay.androidtesting.Validator
import com.vijay.androidtesting.databinding.FragmentAddSpendBinding


class AddSpendFragment : BaseFragment(R.layout.fragment_add_spend) {

    private lateinit var binding: FragmentAddSpendBinding
    private var amount: Int = 0
    private var description: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddSpendBinding.bind(view)

        binding.editTextAmount.addTextChangedListener {
            amount = it.toString().trim().toIntOrNull() ?: 0
            binding.buttonAdd.enabled(Validator.validateInput(amount, description))
        }

        binding.editTextDescription.addTextChangedListener {
            description = it?.toString()?.trim() ?: ""
            binding.buttonAdd.enabled(Validator.validateInput(amount, description))
        }

        binding.buttonAdd.setOnClickListener {
            addSpend()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addSpend() {
        viewModel.addSpend(amount, description)
        findNavController().navigateUp()
    }
}