package com.application.aulatestes

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class TruthTest {

   @Before
   fun setUp() {

   }

   @Test
   fun TesteTruth() {

      /*assertThat(// Afirmar que
         "jamilton"
      ).contains("jamilton")
      assertThat(// Afirmar que
         listOf("jamilton", "ana")
      ).contains("jamilton")
      assertThat(// Afirmar que
         "jamilton"
      ).contains("a")
      val meuItem = listOf("marcelo")
      assertThat(// Afirmar que
         listOf("jamilton", "ana")
      ).containsAnyIn( meuItem )
      assertThat(// Afirmar que
         listOf("jamilton", "ana")
      ).hasSize(2)
      assertThat(// Afirmar que
         "ana"
      ).hasLength(3)
      assertThat(// Afirmar que
         //"jamilton"
         listOf("jamilton", "ana")
      ).isNotEmpty()
      assertThat(// Afirmar que
         true
      ).isEqualTo(false)
      val numero = 5
      assertThat(// Afirmar que
         numero
      ).isIn(8..20)
      val numero = 5
      assertThat(// Afirmar que
         numero
      //).isGreaterThan(10)// Maior do que
      ).isLessThan(10)// Menor do que
      val numero: Int? = null
      assertThat(// Afirmar que
         numero
      //).isNotNull()// Não é Nulo
      ).isNull()// É Nulo
      */
      val resultado: Boolean = true
      assertThat(// Afirmar que
         resultado
      //).isFalse()
      ).isTrue()


   }

   @After
   fun tearDown() {

   }
}