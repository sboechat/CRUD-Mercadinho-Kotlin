package br.com.samuel.mercado.dto

import java.util.UUID

data class ProdutoDto(
    val id: UUID? = null,
    var nome: String,
    var codigoDeBarras: String,
    var valor: Float
)