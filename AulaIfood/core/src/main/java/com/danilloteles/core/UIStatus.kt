package com.danilloteles.core

sealed class UIStatus<out T> {//UIState, Status...
   object Carregando : UIStatus<Nothing>()
   class Sucesso<T>( val dados: T ) : UIStatus<T>()
   class Erro( val erro: String ) : UIStatus<Nothing>()
}