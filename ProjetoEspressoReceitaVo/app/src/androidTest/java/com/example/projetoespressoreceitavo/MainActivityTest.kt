package com.example.projetoespressoreceitavo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule( MainActivity::class.java )

    @Test
    fun testeReciclerView() {

        onView( withId(R.id.rvReceitas) )
            .perform(
            actionOnItemAtPosition<ReceitasAdapter.ReceitaViewHolder>(
                0, click()
            )
        )

        pressBack()

    }
}