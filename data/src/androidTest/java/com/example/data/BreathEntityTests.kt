package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.model.BreathCheck
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Date

@RunWith(AndroidJUnit4::class)
class BreathEntityTests {
    private lateinit var breathDao: BreathDao
    private lateinit var breathDb: BreathDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        breathDb = Room.inMemoryDatabaseBuilder(context, BreathDatabase::class.java).build()
        breathDao = breathDb.breathDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        breathDb.close()
    }

    @Test
    @Throws(IOException::class)
    fun testBreathCheck_insert() {
        val check = BreathCheck(3.0, Date(), 7)
        breathDao.insert(check)
        assertThat(breathDao.getAll()[0], equalTo(check))
    }

    @Test
    @Throws(IOException::class)
    fun testBreathCheck_delete() {
        val check = BreathCheck(3.0, Date(), 7)
        breathDao.insert(check)
        assertThat(breathDao.getAll()[0], equalTo(check))
        breathDao.delete(check)
        assertThat(breathDao.getAll().size, equalTo(0))
    }

    @Test
    @Throws(IOException::class)
    fun testBreathCheck_getByRowId() {
        val check = BreathCheck(4.0, Date())
        assertThat(check.id, equalTo(0))
        val rowId = breathDao.insert(check)
        assertThat(breathDao.getByRowId(rowId).id, equalTo(1))
        assertThat(breathDao.getByRowId(rowId).value, equalTo(check.value))
        assertThat(breathDao.getByRowId(rowId).timestamp, equalTo(check.timestamp))
    }
}
