package com.danilloteles.aularoommvvmpratica.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.danilloteles.aularoommvvmpratica.data.database.BancoDados
import org.junit.After
import org.junit.Before

class AnotacaoDAOTest {

  private lateinit var bancoDados: BancoDados

  @Before
  fun setUp() {
    bancoDados = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      BancoDados::class.java
    ).allowMainThreadQueries().build()
  }

  @After
  fun tearDown() {
    bancoDados.close()
  }
}