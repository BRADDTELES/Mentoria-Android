package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.danilloteles.aularoomdatabase.data.entity.Cliente
import com.danilloteles.aularoomdatabase.data.entity.Pedido
import com.danilloteles.aularoomdatabase.data.entity.Produto
import com.danilloteles.aularoomdatabase.data.entity.ProdutoDetalhe

@Dao
interface ClientePedidoDAO {

    @Insert
    fun salvarCliente( cliente: Cliente ) : Long

    @Insert
    fun salvarPedido( pedido: Pedido ) : Long

    @Query("SELECT * FROM pedidos")
    fun listarClientePedidos() : List<Pedido>

}