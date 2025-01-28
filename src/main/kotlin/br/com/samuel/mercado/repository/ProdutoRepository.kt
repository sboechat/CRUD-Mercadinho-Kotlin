package br.com.samuel.mercado.repository

import br.com.samuel.mercado.entity.Produto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProdutoRepository: JpaRepository<Produto, UUID> {
}