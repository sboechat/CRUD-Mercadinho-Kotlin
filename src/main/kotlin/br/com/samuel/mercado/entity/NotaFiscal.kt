package br.com.samuel.mercado.entity

import br.com.samuel.mercado.dto.NotaFiscalDto
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.OffsetDateTime
import java.util.UUID

@Table(name = "nota_fiscal")
@Entity
class NotaFiscal {

    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID()

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    var idCliente: Cliente? = null

    @OneToMany(mappedBy = "notaFiscal", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var produtos: MutableList<ListaProduto> = mutableListOf()

    var dataCriacao: OffsetDateTime = OffsetDateTime.now()

    fun toDto(): NotaFiscalDto{
        return NotaFiscalDto(id = this.id, idCliente = this.idCliente, produtos = this.produtos, dataCriacao = this.dataCriacao)
    }
}