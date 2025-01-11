package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.aulaifood.databinding.FragmentPedidosBinding
import com.danilloteles.aulaifood.domain.model.PedidoHistorico
import com.danilloteles.aulaifood.presentation.ui.adapter.PedidosHistoricoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PedidosFragment : Fragment() {

   private lateinit var binding: FragmentPedidosBinding
   private val pedidosHistorico = listOf(
      PedidoHistorico(
         "10/10/2025",
         "MC Donalds",
         "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/e35a1e98-0584-4315-afcb-ad6c621ce28a/202408251240_PNLZ.png?imwidth=128",
         listOf("Quarteirão", "Coca-cola")
      ),
      PedidoHistorico(
         "10/09/2025",
         "Burger King",
         "https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/b66d0e6b-465e-45db-af1c-02a625072c95/202408011950_8A10.png",
         listOf("King em Dobro", "Máscara Round 6", "2un. Suco de Uva")
      ),
      PedidoHistorico(
         "25/08/2025",
         "Cabana do Açaí",
         "https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/201810251343_dbcafe23-8fe2-4bb6-ac29-141cbfb2b5c0.jpg",
         listOf("Açaí Tradicional", "Dinamite 750ml")
      ),
      PedidoHistorico(
         "10/08/2025",
         "Dog Mania Hot Dogs e Açai I",
         "https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/0d1040c9-53a3-47c6-8493-e2039bfd2e9b/202409301153_e4yK_i.jpg",
         listOf("Combo - Dog Frango 4 Queijos ", "Batata frita 200gr", "Coca Cola 350 ml")
      ),
   )
   private lateinit var pedidosHistoricoAdapter: PedidosHistoricoAdapter

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = FragmentPedidosBinding.inflate(
         inflater,
         container,
         false
      )

      inicializarPedidosHistorico()
      //inicializarEventosClique()

      return binding.root
   }

   private fun inicializarPedidosHistorico() {
      with( binding ){
         pedidosHistoricoAdapter = PedidosHistoricoAdapter{ pedidosHistorico ->

         }
         pedidosHistoricoAdapter.adicionarItens( pedidosHistorico )
         rvPedidosHistorico.adapter = pedidosHistoricoAdapter
         rvPedidosHistorico.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
         )
      }
   }

}