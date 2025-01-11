package com.danilloteles.aulaifood.domain.usecase.produto

import com.danilloteles.aulaifood.data.remote.firebase.repository.produto.IProdutoRepository
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.aulaifood.domain.model.ProdutosSeparados
import com.danilloteles.aulaifood.domain.model.TipoProduto
import com.danilloteles.core.UIStatus
import javax.inject.Inject

class RecuperarProdutosPorTipoUseCase @Inject constructor(
   private val produtoRepositoryImpl: IProdutoRepository
) {

   suspend operator fun invoke(
      idLoja: String,
      uiStatus: (UIStatus<List<ProdutosSeparados>>) -> Unit
   ){

      produtoRepositoryImpl.listar( idLoja ){ statusRetorno ->
         when( statusRetorno ){
            is UIStatus.Sucesso -> {
               val listaProdutos = statusRetorno.dados

               val produtosEmDestaque = listaProdutos
                  .filter { it.emDestaque == true }

               val produtos = listaProdutos
                  .filter { it.emDestaque == false }

               val produtosSeparados = listOf(
                  ProdutosSeparados( TipoProduto.PRODUTOS_EM_DESTAQUE, produtosEmDestaque ),
                  ProdutosSeparados( TipoProduto.PRODUTOS, produtos ),
               )

               uiStatus.invoke(UIStatus.Sucesso( produtosSeparados ))

            }
            is UIStatus.Carregando -> uiStatus.invoke( UIStatus.Carregando )
            is UIStatus.Erro -> uiStatus.invoke( UIStatus.Erro( statusRetorno.erro ) )
         }
      }

   }

}