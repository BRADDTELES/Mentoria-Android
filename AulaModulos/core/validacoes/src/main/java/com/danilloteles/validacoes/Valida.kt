package com.danilloteles.validacoes

//Pública ou aberta aos módulos
object Valida {
    fun validarData() {
        ValidacaoCampos.validarCampoNumerico()
    }
    fun validarEmail() {
        ValidacaoCampos.validarCampoEmail()
        ValidacaoCampos.validarCampoVazio()
    }
}