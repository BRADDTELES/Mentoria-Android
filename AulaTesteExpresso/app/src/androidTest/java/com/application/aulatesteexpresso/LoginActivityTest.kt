package com.application.aulatesteexpresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {

   @get:Rule
   val activityScenarioRule = ActivityScenarioRule( LoginActivity::class.java )

   @Test
   fun verificarLogin_preencheDadosUsuario_check() {

      onView( withId(R.id.editSenha) )
         //.check( matches( withText("Logar") ) )
         //.check( matches( withHint("Digite seu e-mail") ) )
         //.check( matches( not(isDisplayed()) ) )
         .check( matches( isDisplayed() ) )

      /*onView( withId(R.id.btnLogar) )
         .check( matches( withText("Logar") ) )*/

   }

   @Test
   fun verificarLogin_preencheDadosUsuario() {

      onView( withId(R.id.btnLogar) )
         //.check( matches( isDisplayed() ) )
         //.check( matches( isCompletelyDisplayed() ) )
         //.check( matches( isEnabled() ) )
         //.check( matches( isClickable() ) )
         //.check( matches( withEffectiveVisibility( ViewMatchers.Visibility.VISIBLE ) ) )
         .check( matches( isChecked() ) )// Verifica se o item selecionado está marcado

      /*onView( withHint("Digite seu e-mail") )
         .perform( typeText("j@gmail.com") )*/

      /*onView( withText("Logar") )
         .perform( click() )*/

      /*val email = "ja@gmail.com"
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
      onView( withId(R.id.editSenha) ).perform( clearText() )*/
   }
}