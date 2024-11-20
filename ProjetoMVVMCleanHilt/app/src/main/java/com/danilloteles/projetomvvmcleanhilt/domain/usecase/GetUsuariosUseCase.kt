package com.danilloteles.projetomvvmcleanhilt.domain.usecase
import com.danilloteles.projetomvvmcleanhilt.domain.model.Usuario
import com.danilloteles.projetomvvmcleanhilt.domain.repository.UsuarioRepository
import javax.inject.Inject

class GetUsuariosUseCase @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) {

    //Data -> UsuarioDTO
    //Domínio -> Usuario
    //Presentation -> UsuarioPresentation
    suspend operator fun invoke() : List<Usuario> {
        return try {

            //Regras de negócios
            usuarioRepository.resuperarUsuarios()

            /* Exemplo de outras Regras de negpocio *//*
                //Verificar os usuários mais próximos AppUber
                getMotoristaUseCase.recuperarMotorista()
                getMotoristaUseCase.notificarMotorista()*/

        }catch (erroRecuperarUsuarios: Exception){
            erroRecuperarUsuarios.printStackTrace()
            emptyList()
        }
    }

}