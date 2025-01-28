package br.com.samuel.mercado.entity

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Table(name = "nota_fiscal")
@Entity
class NotaFiscal {

    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID()

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    var idCliente: Cliente? = null

    @OneToMany(mappedBy = "notaFiscal", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var produtos: MutableList<ListaProduto> = mutableListOf()
}