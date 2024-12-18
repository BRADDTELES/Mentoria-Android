package com.application.aulatestes

class UsuarioSistema {

    fun cadastrarUsuario(
        email: String,
        senha: String,
        senhaConfirmacao: String
    ) : Boolean {

        if ( email.isEmpty() )
            return false

        if ( senha.isEmpty() )
            return false

        if (senha != senhaConfirmacao)
            return false

        //Cadastro de usuario
        return true

    }

    fun calcularDesconto(
        salario: Double,
        desconto: Double
    ) : Double {
        return salario - desconto
    }

    fun logarUsuario(){}
}