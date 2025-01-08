package com.danilloteles.core

sealed class UIStatus<out T> {//UIState, Status...
   class Sucesso<T>( dados: T ) : UIStatus<T>()
   class Erro( val erro: String ) : UIStatus<Nothing>()
}