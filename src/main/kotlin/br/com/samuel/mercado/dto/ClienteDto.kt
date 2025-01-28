package br.com.samuel.mercado.dto

import java.util.UUID

data class ClienteDto(
    val id: UUID? = null,
    var nome: String,
    var cpf: String
)