package com.application.aulatesteexpresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

   @get:Rule
   val activityScenarioRule = ActivityScenarioRule( LoginActivity::class.java )

   @Test
   fun verificarLogin_preencheDadosUsuario() {

      onView( withId( R.id.btnLogar ) ).perform( click() )

   }
}