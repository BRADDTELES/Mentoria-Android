package com.application.aulatesteexpresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

   @get:Rule
   val activityScenarioRule = ActivityScenarioRule( LoginActivity::class.java )

   @Test
   fun verificarLogin_preencheDadosUsuario() {

      val email = "ja@gmail.com"

      //Digitar texto
      onView( withId(R.id.editEmail) )
         .perform( typeText(email) )

      //Clique ddo botão
      onView( withId(R.id.btnLogar) ).perform( click() )

      //Veriffa se combina com o texto passado
      onView( withId(R.id.textEmail) )
         .check( matches(withText(email)) )

   }
}