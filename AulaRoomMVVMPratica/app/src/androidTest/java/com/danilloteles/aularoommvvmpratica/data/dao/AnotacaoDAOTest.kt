package com.danilloteles.aularoommvvmpratica.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.danilloteles.aularoommvvmpratica.data.database.BancoDados
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class AnotacaoDAOTest {

  private lateinit var bancoDados: BancoDados
  private lateinit var categoriaDAO: CategoriaDAO

  @Before
  fun setUp() {
    bancoDados = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      BancoDados::class.java
    ).allowMainThreadQueries().build()
    categoriaDAO = bancoDados.categoriaDAO
  }

  @Test
  fun salvarCategoria_verificaCategoriaCadastrada_retornaTrue() {
    val categoria = Categoria(0, "mercado")
    val idCategoria = categoriaDAO.salvar( categoria )
    assertThat( idCategoria ).isGreaterThan(0L)
  }

  @After
  fun tearDown() {
    bancoDados.close()
  }
}