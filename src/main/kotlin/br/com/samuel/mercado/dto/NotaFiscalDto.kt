package br.com.samuel.mercado.dto

import br.com.samuel.mercado.entity.Cliente
import br.com.samuel.mercado.entity.ListaProduto
import java.time.OffsetDateTime
import java.util.UUID

data class NotaFiscalDto (
    val id: UUID? = null,
    var idCliente: Cliente? = null,
    var produtos: MutableList<ListaProduto>,
    var dataCriacao: OffsetDateTime = OffsetDateTime.now()
)