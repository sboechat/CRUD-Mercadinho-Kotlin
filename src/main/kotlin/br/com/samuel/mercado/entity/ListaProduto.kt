package br.com.samuel.mercado.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Table(name = "lista_produto")
@Entity
class ListaProduto {

    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID()

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id", nullable = false)
    var notaFiscal: NotaFiscal? = null

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    var produto: Produto? = null

    @Column(name = "quantidade_produto")
    var quantidadeProduto: Int = 0
}