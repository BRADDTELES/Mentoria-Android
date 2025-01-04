package com.danilloteles.aularoomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.danilloteles.aularoomdatabase.data.entity.Computador
import com.danilloteles.aularoomdatabase.data.entity.Pedido
import com.danilloteles.aularoomdatabase.data.entity.Pessoa
import com.danilloteles.aularoomdatabase.data.entity.PessoaComputador

data class PessoaComComputadores(
    @Embedded
    val pessoa: Pessoa,
    @Relation(
        parentColumn = "id_pessoa",//Chave primária
        entityColumn = "id_computador",//chave primária
        entity = Computador::class,
        associateBy = Junction(PessoaComputador::class)
    )
    val computadores: List<Computador>
)

/*data class ComputadorComPessoas(
    @Embedded
    val computador: Computador,
    @Relation(
        parentColumn = "id_computador",//Chave primária
        entityColumn = "id_pessoa",//chave primária
        entity = Pessoa::class,
        associateBy = Junction(PessoaComputador::class)
    )
    val pessoas: List<Pessoa>
)*/
