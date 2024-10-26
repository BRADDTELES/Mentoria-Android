package com.jamiltondamasceno.projetonetflixapi.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

data class Endereco(
    val bairro: String,
    val complemento: String,
    val logradouro: String,
    val localidade: String,
    val estado: String,
    val uf: String
)

/*//Raiz do XML - Usando uma data class
@Root(name = "xmlcep", strict = false) // strict -> false para permitir atributos não mapeados / true para permitir atributos por padrão
data class Endereco( // data class Endereço

    @field: Element(name = "bairro")//Atributo de classe (campo)
    @param: Element(name = "bairro")//Paramêtro do construtor
    val bairro: String,

    @field: Element(name = "complemento")
    @param: Element(name = "complemento")
    val complemento: String,

    @field: Element(name = "logradouro")
    @param: Element(name = "logradouro")
    val logradouro: String,

    @field: Element(name = "localidade")
    @param: Element(name = "localidade")
    val localidade: String,

    @field: Element(name = "estado")
    @param: Element(name = "estado")
    val estado: String,

    @field: Element(name = "uf")
    @param: Element(name = "uf")
    val uf: String
)*/

/*//Raiz do XML - Usando uma Classe
@Root(name = "xmlcep", strict = false) // strict -> false para permitir atributos não mapeados / true para permitir atributos por padrão
class Endereco{
    @field: Element(name = "bairro")//Atributo de classe (campo)
    var bairro: String = ""

    @field: Element(name = "complemento")
    var complemento: String = ""

    @field: Element(name = "logradouro")
    var logradouro: String = ""

    @field: Element(name = "localidade")
    var localidade: String = ""

    @field: Element(name = "estado")
    var estado: String = ""

    @field: Element(name = "uf")
    var uf: String = ""
}*/


