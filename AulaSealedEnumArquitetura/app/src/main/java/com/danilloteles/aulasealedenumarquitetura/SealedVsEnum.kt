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

/*open class Veiculo //Supertipo (Veiculo)
class Carro : Veiculo() //Subtipo (Veiculo e também carro)*/

/*open class Supertipo //pai
class Subtipo : Supertipo() //filha

interface Corariant<out T>
interface ContraCorariant<in T>*/



/*class UsuarioRepository{
    fun salvarUsuario(usuario: Usuario, retornoRequisicao: (StatusRequisicao) -> Unit ) {
        val lista = listOf("jamilton", "ana", "maria")
        //val status = StatusRequisicao.Sucesso( lista )
        val status = StatusRequisicao.Erro(10, "Erro comunicação API")
        retornoRequisicao.invoke( status )
    }
}*/

/*class UsuarioViewModel{
    fun salvarUsuario(usuario: Usuario, retornoRequisicao: (StatusRequisicao) -> Unit ){
        val usuarioRepository = UsuarioRepository()
        val listaUsuarios = usuarioRepository.salvarUsuario( usuario, retornoRequisicao )
    }
}*/

sealed class RetornoRequisicao<out T> {
    //Armazena objetos (estados diferentes)
    class Sucesso<T>(var dados: T) : RetornoRequisicao<T>()
    class Erro(var erro: String) : RetornoRequisicao<Nothing>()
    //object Nenhhum : RetornoRequisicao()
}

data class Usuario( private val nome: String){

}

class UsuarioRepository{
    fun salvar(
        usuario: Usuario,
        resultadoRequisicao: (RetornoRequisicao<List<Usuario>>) -> Unit
    ) {
        //Chamada da API
        val retorno = RetornoRequisicao.Sucesso(
            //listOf("jamilton", "ana")
            listOf( Usuario("Jamilton"), Usuario("Ana"))
        )
        //val retorno = RetornoRequisicao.Erro("400")
        resultadoRequisicao.invoke( retorno )
    }
}

class UsuarioViewModel{
    fun adicionarUsuario(
        usuario: Usuario,
        resultadoRequisicao: (RetornoRequisicao<List<Usuario>>) -> Unit
    ){
        val usuarioRepository = UsuarioRepository()
        usuarioRepository.salvar( usuario, resultadoRequisicao )
    }
}

sealed class StatusRequisicao<out T> {
    //Armazena objetos
    class Sucesso<T>(var dados: T ) : StatusRequisicao<T>()
    class Erro(var mensagemErro: String) : StatusRequisicao<Nothing>()
}

fun main() {

    val usuarioViewModel = UsuarioViewModel()

    //DENTRO DA ACTIVITY
    val usuario = Usuario("Jamilton")//Dados da interface
    usuarioViewModel.adicionarUsuario( usuario ){ RetornoRequisicao ->
        when( RetornoRequisicao ){
            is RetornoRequisicao.Sucesso -> println("Sucesso Sealed: ${RetornoRequisicao.dados}")
            is RetornoRequisicao.Erro -> println("Erro Sealed status: ${RetornoRequisicao.erro}")
        }
    }


    /*//Sealed Class
    val statusRequisicao: StatusRequisicao<List<Usuario>> = StatusRequisicao.Sucesso(
        listOf(Usuario("Jamilton"), Usuario("Ana"))
        //listOf( "jamilton", "ana" )
    )
    when ( statusRequisicao ){
        is StatusRequisicao.Sucesso -> println("Sucesso lista: ${statusRequisicao.dados}")
        is StatusRequisicao.Erro -> println("Erro: ${statusRequisicao.mensagemErro}}")
    }*/


    //val supertipo: Corariant<Supertipo> = object : Corariant<Subtipo>{}
    //val supertipo: ContraCorariant<Subtipo> = object : ContraCorariant<Supertipo>{}

    //val veiculo: Carro = Veiculo()


    /*val status = StatusPedido.AGUARDANDO
    if ( status == StatusPedido.AGUARDANDO ){}*/


    //Enum
    /*val statusRequisicao = StatusRequisicaoEnum.Sucesso
    when( statusRequisicao ){
        StatusRequisicaoEnum.Sucesso -> println("Sucesso")
        StatusRequisicaoEnum.ERRO -> println("Erro")
    }*/


    /*//Activity
    val usuario = Usuario("Jamilton")
    val usuarioViewModel = UsuarioViewModel()
    usuarioViewModel.salvarUsuario( usuario ){ statusRequisicao ->
        when ( statusRequisicao ){
            is StatusRequisicao.Sucesso -> println("Sucesso lista: ${statusRequisicao.lista}")
            is StatusRequisicao.Erro -> println("Erro: ${statusRequisicao.codigo} desc: ${statusRequisicao.descricao}")
        }
    }*/

}