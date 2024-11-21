package com.danilloteles.aulasealedenumarquitetura

/*object Constantes{
    const val AGUARDANDO = "aguardando"
}*/

/*enum class StatusPedido{//Dados constantes
    AGUARDANDO, PROCESSANDO, ANALISE_PAGAMENTO, ENVIANDO
}*/

/*class Status {
    private val nome: String = ""
    private val status: StatusRequisicaoEnum = StatusRequisicaoEnum.SUCESSO
}*/

/*enum class StatusRequisicaoEnum {//Armazena constantes
    Sucesso, ERRO
}*/

/*sealed interface StatusRequisicao {
    //Armazena objetos
    class Sucesso(var lista: List<String>) : StatusRequisicao
    class Erro(var codigo: Int, var descricao: String) : StatusRequisicao
}*/

sealed class StatusRequisicao {
    //Armazena objetos
    class Sucesso(var lista: List<String>) : StatusRequisicao()
    class Erro(var codigo: Int, var descricao: String) : StatusRequisicao()
}

data class Usuario(val nome: String)

class UsuarioRepository{
    fun salvarUsuario(usuario: Usuario, retornoRequisicao: (StatusRequisicao) -> Unit ) {
        val lista = listOf("jamilton", "ana", "maria")
        //val status = StatusRequisicao.Sucesso( lista )
        val status = StatusRequisicao.Erro(10, "Erro comunicação API")
        retornoRequisicao.invoke( status )
    }
}

class UsuarioViewModel{
    fun salvarUsuario(usuario: Usuario, retornoRequisicao: (StatusRequisicao) -> Unit ){
        val usuarioRepository = UsuarioRepository()
        val listaUsuarios = usuarioRepository.salvarUsuario( usuario, retornoRequisicao )
    }
}
open class Veiculo //Supertipo (Veiculo)
class Carro : Veiculo() //Subtipo (Veiculo e também carro)

/*open class Supertipo //pai
class Subtipo : Supertipo() //filha

interface Corariant<out T>
interface ContraCorariant<in T>*/

fun main() {

    //Activity
    val usuario = Usuario("Jamilton")
    val usuarioViewModel = UsuarioViewModel()
    usuarioViewModel.salvarUsuario( usuario ){ statusRequisicao ->
        when ( statusRequisicao ){
            is StatusRequisicao.Sucesso -> println("Sucesso lista: ${statusRequisicao.lista}")
            is StatusRequisicao.Erro -> println("Erro: ${statusRequisicao.codigo} desc: ${statusRequisicao.descricao}")
        }
    }




    //val supertipo: Corariant<Supertipo> = object : Corariant<Subtipo>{}
    //val supertipo: ContraCorariant<Subtipo> = object : ContraCorariant<Supertipo>{}

    //val veiculo: Carro = Veiculo()




    /*val status = StatusPedido.AGUARDANDO
    if ( status == StatusPedido.AGUARDANDO ){}*/

    //Sealed Class
    val statusRequisicao: StatusRequisicao = StatusRequisicao.Sucesso(
        listOf("jamilton", "ana", "maria")
    )
    when ( statusRequisicao ){
        is StatusRequisicao.Sucesso -> println("Sucesso lista: ${statusRequisicao.lista}")
        is StatusRequisicao.Erro -> println("Erro: ${statusRequisicao.codigo} desc: ${statusRequisicao.descricao}")
    }

    //Enum
    /*val statusRequisicao = StatusRequisicaoEnum.Sucesso
    when( statusRequisicao ){
        StatusRequisicaoEnum.Sucesso -> println("Sucesso")
        StatusRequisicaoEnum.ERRO -> println("Erro")
    }*/

}