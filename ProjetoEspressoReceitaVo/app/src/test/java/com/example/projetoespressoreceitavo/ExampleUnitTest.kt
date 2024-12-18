package com.example.projetoespressoreceitavo

import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
class ExampleUnitTest {

    @LargeTest
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @MediumTest
    @Test
    fun addition_isCorrect2() {
        assertEquals(4, 2 + 2)
    }

    /*@SmallTest
    @Test
    fun addition_isCorrect3() {
        assertEquals(4, 2 + 2)
    }*/
}