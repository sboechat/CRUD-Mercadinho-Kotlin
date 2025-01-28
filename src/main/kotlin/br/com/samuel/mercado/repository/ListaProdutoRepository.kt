package br.com.samuel.mercado.repository

import br.com.samuel.mercado.entity.ListaProduto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ListaProdutoRepository: JpaRepository<ListaProduto, UUID> {
}