package br.com.samuel.mercado.repository

import br.com.samuel.mercado.entity.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClienteRepository: JpaRepository<Cliente, UUID>{
    fun findByCpf(cpf: String):Cliente
}