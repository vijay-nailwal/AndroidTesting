package com.vijay.androidtesting.db

import com.vijay.androidtesting.db.Spend
import com.vijay.androidtesting.db.SpendDao

class SpendsTrackerDataSource(
    private val db: SpendDao
) {
    suspend fun addSpend(spend: Spend) = db.addSpend(spend)

    suspend fun getLast20Spends() = db.getLast20Spends()
}