package com.danilloteles.aovivoturma02expresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule( MainActivity::class.java )

    @LargeTest
    @Test
    fun testeInterfaceLogin() {

        onView( withId(R.id.editEmail) )//Elemento da interface
            .perform( replaceText("ja@gmail.com") )//Executa ação

        onView( withId(R.id.btnLogar) )//Elemento da interface
            .perform( click() )//Executa ação

        onView(withId(R.id.textDetalhe))
            //.check( matches(withText("Jamilton Damasceno")) )
            .check( matches(isDisplayed()) )

    }
}