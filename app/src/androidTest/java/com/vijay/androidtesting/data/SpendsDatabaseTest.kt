package com.vijay.androidtesting.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.vijay.androidtesting.db.Spend
import com.vijay.androidtesting.db.SpendDao
import com.vijay.androidtesting.db.SpendsDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class SpendsDatabaseTest : TestCase() {

    private lateinit var db: SpendsDatabase
    private lateinit var dao: SpendDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpendsDatabase::class.java).build()
        dao = db.getSpendDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadSpend() = runBlocking {
        val date = Date()
        val spend = Spend(date, 100, "Bought something")
        dao.addSpend(spend)
        val spends = dao.getLast20Spends()
        assertThat(spends.contains(spend)).isTrue()
    }
}