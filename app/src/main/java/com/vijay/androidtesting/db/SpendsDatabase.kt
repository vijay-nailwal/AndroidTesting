package com.vijay.androidtesting.db

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase

@Database(entities = [Spend::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class SpendsDatabase : RoomDatabase() {

    abstract fun getSpendDao(): SpendDao

    companion object {
        private const val DB_NAME = "Spends-Database.db"

        @Volatile
        private var instance: SpendsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SpendsDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}