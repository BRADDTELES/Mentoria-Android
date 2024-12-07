package com.danilloteles.aulatesteapipratico.view.viewmodel

import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import com.danilloteles.aulatesteapipratico.data.remote.repository.IUsuarioRepository

class UsuarioUseCaseFake{

   suspend operator fun invoke() : List<Usuario>{//getListUserCase
      return listOf(
         Usuario(10, "j@gmail.com", "Jamilton", "M"),
         Usuario(23, "ana@gmail.com", "Ana", "F"),
         Usuario(45, "joao@gmail.com", "João", "M")
      )
   }

}