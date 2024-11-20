package com.danilloteles.projetomvvmcleanhilt.data.dto

import com.danilloteles.projetomvvmcleanhilt.domain.model.Usuario

data class UsuarioDTO(
    val address: Address,
    val age: Int,
    val bank: Bank,
    val birthDate: String,
    val bloodGroup: String,
    val company: Company,
    val crypto: Crypto,
    val ein: String,
    val email: String,
    val eyeColor: String,
    val firstName: String,
    val gender: String,
    val hair: Hair,
    val height: Double,
    val id: Int,
    val image: String,
    val ip: String,
    val lastName: String,
    val macAddress: String,
    val maidenName: String,
    val password: String,
    val phone: String,
    val role: String,
    val ssn: String,
    val university: String,
    val userAgent: String,
    val username: String,
    val weight: Double
)

// Função de extensão para converter um objeto UsuarioDTO em um objeto Usuario
fun UsuarioDTO.toUsuario() : Usuario {
    return Usuario(
        nome = this.firstName,
        sobrenome = this.lastName,
        endereco = this.address.address,
        idade = this.age,
        email = this.email,
        telefone = this.phone,
        imagem = this.image
    )
}