package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.danilloteles.aularoomdatabase.data.entity.Cliente
import com.danilloteles.aularoomdatabase.data.entity.Pedido
import com.danilloteles.aularoomdatabase.data.entity.Produto
import com.danilloteles.aularoomdatabase.data.entity.ProdutoDetalhe
import com.danilloteles.aularoomdatabase.data.entity.relation.ClienteComPedidos
import com.danilloteles.aularoomdatabase.data.entity.relation.ProdutoEProdutoDetalhe

@Dao
interface ClientePedidoDAO {

    @Insert
    fun salvarCliente( cliente: Cliente ) : Long

    @Insert
    fun salvarPedido( pedido: Pedido ) : Long

    /*//Minha Resposta do Desafio
    @Query("SELECT * FROM pedidos")
    fun listarClientePedidos() : List<Pedido>*/

    @Transaction
    @Query("SELECT * FROM clientes")
    fun listarClientesComPedidos() : List<ClienteComPedidos>

}