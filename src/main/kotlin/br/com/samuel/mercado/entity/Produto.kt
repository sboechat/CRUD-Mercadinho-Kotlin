package br.com.samuel.mercado.entity

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

    var codigoDeBarras: Long = 0

    var valor: Float = 0f;
}