package com.example.myguysapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

    @Dao
    interface MyGuysDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun tambahKawan(guys: MyGuys)

        @Query("SELECT * FROM MyGuys")
        fun ambilSemuaKawan(): LiveData<List<MyGuys>>
    }
