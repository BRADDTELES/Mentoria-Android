package com.application.applistatarefas.database

import com.application.applistatarefas.model.Tarefa

interface ITarefaDAO {

    fun salvar(tarefa: Tarefa):Boolean
    fun atualizar(tarefa: Tarefa):Boolean
    fun remover(idTarefa: Int):Boolean
    fun listar():List<Tarefa>

}