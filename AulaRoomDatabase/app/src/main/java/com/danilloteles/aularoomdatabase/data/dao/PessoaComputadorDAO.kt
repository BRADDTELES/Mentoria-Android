package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.danilloteles.aularoomdatabase.data.entity.Cliente
import com.danilloteles.aularoomdatabase.data.entity.Computador
import com.danilloteles.aularoomdatabase.data.entity.Pedido
import com.danilloteles.aularoomdatabase.data.entity.Pessoa
import com.danilloteles.aularoomdatabase.data.entity.PessoaComputador
import com.danilloteles.aularoomdatabase.data.entity.relation.ClienteComPedidos
import com.danilloteles.aularoomdatabase.data.entity.relation.PessoaComComputadores

@Dao
interface PessoaComputadorDAO {

    @Insert
    fun salvarPessoa( pessoa: Pessoa ) : Long

    @Insert
    fun salvarComputador( computador: Computador ) : Long

    @Insert
    fun salvarPessoaComputador( pessoaComputador: PessoaComputador ) : Long

    @Transaction
    @Query("SELECT * FROM pessoas")
    fun listarPessoasComComputadores() : List<PessoaComComputadores>

}