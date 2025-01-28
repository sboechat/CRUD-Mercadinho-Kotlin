package br.com.samuel.mercado.entity

import br.com.samuel.mercado.dto.ClienteDto
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Table(name = "cliente")
@Entity
class Cliente {

    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID()

    var nome: String = ""

    var cpf: String = ""

    fun toDto(): ClienteDto{
        return ClienteDto(id = this.id, nome = this.nome, cpf = this.cpf)
    }
}