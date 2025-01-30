package br.com.samuel.mercado.dto

import br.com.samuel.mercado.entity.NotaFiscal
import br.com.samuel.mercado.entity.Produto
import java.util.UUID

data class ListaProdutoDto(
    val id: UUID?,
    var notaFiscal: NotaFiscal?,
    var produto: Produto?,
    var quantidadeProduto: Int
)
