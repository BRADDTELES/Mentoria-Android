package com.application.aulasqliteandroid.database

import android.content.Context
import android.util.Log
import com.application.aulasqliteandroid.model.Produto

class ProdutoDAO(context: Context): IProdutoDAO {

    val escrita = DatabaseHelper(context).writableDatabase
    val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(produto: Produto): Boolean {

        val titulo = produto.titulo
        val sql = "INSERT INTO ${DatabaseHelper.TABELA_PRODUTOS} " +
                "VALUES(null, '$titulo', 'Descrição...');"

        try {
            escrita.execSQL( sql )
            Log.i("info_db", "Sucesso ao inserir")
        }catch (e: Exception){
            Log.i("info_db", "Erro ao inserir")
            return false
        }

        return true

    }

    override fun atualizar(produto: Produto): Boolean {

        val titulo = produto.titulo
        val idProduto = produto.idProduto
        val sql = "UPDATE ${DatabaseHelper.TABELA_PRODUTOS} " +
                "SET ${DatabaseHelper.TITULO} = '$titulo' " +
                "WHERE ${DatabaseHelper.ID_PRODUTO} = $idProduto;"

        try {
            escrita.execSQL( sql )
            Log.i("info_db", "Sucesso ao atualizar")
        }catch (e: Exception){
            Log.i("info_db", "Erro ao atualizar")
            return false
        }

        return true

    }

    override fun remover(idProduto: Int): Boolean {

        val sql = "DELETE FROM ${DatabaseHelper.TABELA_PRODUTOS} " +
                "WHERE ${DatabaseHelper.ID_PRODUTO} = $idProduto;"

        try {
            escrita.execSQL( sql )
            Log.i("info_db", "Sucesso ao remover")
        }catch (e: Exception){
            Log.i("info_db", "Erro ao remover")
            return false
        }

        return true

    }

    override fun listar(): List<Produto> {

        val listaProdutos = mutableListOf<Produto>()

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"
        val cursor = leitura.rawQuery( sql, null )
        // cursor.moveToFirst() // Colocar o cursor no primeiro registro
        // cursor.moveToNext() // Avançar para o próximo registro

        val indiceId = cursor.getColumnIndex( "${DatabaseHelper.ID_PRODUTO}" )
        val indiceTitulo = cursor.getColumnIndex( "${DatabaseHelper.TITULO}" )
        val indiceDescricao = cursor.getColumnIndex( "${DatabaseHelper.DESCRICAO}" )

        while ( cursor.moveToNext() ){//false ou true

            val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
            //Log.i("info_db", "id: $idProduto - $titulo")

            listaProdutos.add(
                Produto(idProduto, titulo, descricao)
            )

        }

        return listaProdutos

    }

}