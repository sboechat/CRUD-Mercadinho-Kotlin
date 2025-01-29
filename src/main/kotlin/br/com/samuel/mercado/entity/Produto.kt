package br.com.samuel.mercado.entity

import br.com.samuel.mercado.dto.ProdutoDto
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Table(name = "produto")
@Entity
class Produto {

    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID()

    var nome: String = ""

    var codigoDeBarras: String = ""

    var valor: Float = 0f

    fun toDto(): ProdutoDto{
        return ProdutoDto(id = this.id, nome = this.nome, codigoDeBarras = this.codigoDeBarras, valor = this.valor)
    }
}