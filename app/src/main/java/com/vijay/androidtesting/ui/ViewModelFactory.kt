package com.vijay.androidtesting.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vijay.androidtesting.db.SpendsTrackerDataSource

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val dataSource: SpendsTrackerDataSource
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpendViewModel(dataSource) as T
    }
}