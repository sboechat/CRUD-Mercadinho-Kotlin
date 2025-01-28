package br.com.samuel.mercado.repository

import br.com.samuel.mercado.entity.NotaFiscal
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NotaFiscalRepository: JpaRepository<NotaFiscal, UUID> {
}