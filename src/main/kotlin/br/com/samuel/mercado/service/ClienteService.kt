package br.com.samuel.mercado.service

import br.com.samuel.mercado.dto.ClienteDto
import br.com.samuel.mercado.entity.Cliente
import br.com.samuel.mercado.repository.ClienteRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ClienteService(
    @Autowired private val clienteRepository: ClienteRepository
) {

    fun getClienteByCpf(cpf: String): ClienteDto {
        try {
            return clienteRepository.findByCpf(cpf).toDto()
        }catch (e: Exception){
            throw EntityNotFoundException("Cliente com CPF '$cpf' não encontrado.")
        }
    }

    fun getClienteById(id: UUID): ClienteDto{
        val cliente = clienteRepository.findById(id)
            .orElseThrow{ EntityNotFoundException("Cliente com id: $id não encontrado.") }
        return cliente.toDto()
    }

    fun getTodosClientes(): List<ClienteDto> {
        try {
            val clientes = clienteRepository.findAll()
            val clientesDto = clientes.map { it.toDto() }
            return clientesDto
        }catch (e: Exception){
            throw RuntimeException("Erro ao buscar todos os cliente: ${e.message}", e)
        }
    }

    fun addCliente(clienteDto: ClienteDto) {
        try{
            val cliente = Cliente()
            cliente.nome = clienteDto.nome
            cliente.cpf = clienteDto.cpf
            clienteRepository.save(cliente)
        } catch (e: Exception) {
            throw RuntimeException("Erro ao adicionar cliente: ${e.message}", e)
        }
    }

    fun updateCliente(clienteDto: ClienteDto){
        try{
            if (clienteDto.id != null) {
                val clienteAtualizado = clienteRepository.findById(clienteDto.id)
                    .orElseThrow { EntityNotFoundException("Cliente com ID ${clienteDto.id} não encontrado.") }

                clienteAtualizado.cpf = clienteDto.cpf
                clienteAtualizado.nome = clienteDto.nome

                clienteRepository.save(clienteAtualizado)
            }else{
                throw IllegalArgumentException("É necessário fornecer o ID do cliente.")
            }
        } catch (e: Exception) {
            throw RuntimeException("Erro ao atualizar cliente: ${e.message}", e)
        }

    }

    fun deleteClienteById(id: UUID){
        try {
            val cliente = clienteRepository.findById(id)
                .orElseThrow { EntityNotFoundException("Cliente com ID ${id} não encontrado.") }
            clienteRepository.delete(cliente)
        }catch (e: Exception){
            throw RuntimeException("Erro ao excluir cliente: ${e.message}", e)
        }
    }

}