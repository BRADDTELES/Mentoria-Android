package com.application.aulatesteexpresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
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
      val senha = "1234"

      //Digitar email e senha
      onView( withId(R.id.editEmail) ).perform( typeText(email) )
      onView( withId(R.id.editSenha) ).perform( typeText(senha) )

      //Clique ddo botão logar - LoginActivity
      onView( withId(R.id.btnLogar) ).perform( click() )

      //Veriffa se combina com o texto passado - MainActivity
      onView( withId(R.id.textEmail) )
         .check( matches(withText(email)) )

      //Clique ddo botão voltar - MainActivity
      onView( withId(R.id.btnVoltar) ).perform( click() )

      //Limpar o texto - LoginActivity
      onView( withId(R.id.editEmail) ).perform( clearText() )
      onView( withId(R.id.editSenha) ).perform( clearText() )
   }
}