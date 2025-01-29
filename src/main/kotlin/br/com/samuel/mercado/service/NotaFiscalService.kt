package br.com.samuel.mercado.service

import br.com.samuel.mercado.dto.NotaFiscalDto
import br.com.samuel.mercado.entity.NotaFiscal
import br.com.samuel.mercado.repository.NotaFiscalRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class NotaFiscalService(
    @Autowired private val notaFiscalRepository: NotaFiscalRepository
) {

    fun getNotaFiscalById(id: UUID): NotaFiscalDto{
        val notaFiscal = notaFiscalRepository.findById(id)
            .orElseThrow{ EntityNotFoundException("Nota fiscal com id: $id não encontrado.") }
        return notaFiscal.toDto()
    }

    fun getTodasNotasFiscais(): List<NotaFiscalDto> {
        try {
            val notasFiscais = notaFiscalRepository.findAll()
            val notasFiscaisDto = notasFiscais.map { it.toDto() }
            return notasFiscaisDto
        }catch (e: Exception){
            throw RuntimeException("Erro ao buscar todos os nota fiscal: ${e.message}", e)
        }
    }

    fun addNotaFiscal(notaFiscalDto: NotaFiscalDto) {
        try{
            val notaFiscal = NotaFiscal()
            notaFiscal.idCliente = notaFiscalDto.idCliente
            notaFiscal.produtos = notaFiscalDto.produtos
            notaFiscalRepository.save(notaFiscal)
        } catch (e: Exception) {
            throw RuntimeException("Erro ao atualizar nota fiscal: ${e.message}", e)
        }
    }

    fun updateNotaFiscal(notaFiscalDto: NotaFiscalDto){
        try{
            if (notaFiscalDto.id != null) {
                val notaFiscalAtualizado = notaFiscalRepository.findById(notaFiscalDto.id)
                    .orElseThrow { EntityNotFoundException("Nota fiscal com ID ${notaFiscalDto.id} não encontrado.") }

                notaFiscalAtualizado.idCliente = notaFiscalDto.idCliente
                notaFiscalAtualizado.produtos = notaFiscalDto.produtos

                notaFiscalRepository.save(notaFiscalAtualizado)
            }else{
                throw IllegalArgumentException("É necessário fornecer o ID do nota fiscal.")
            }
        } catch (e: Exception) {
            throw RuntimeException("Erro ao atualizar nota fiscal: ${e.message}", e)
        }

    }

    fun deleteNotaFiscalById(id: UUID){
        try {
            val notaFiscal = notaFiscalRepository.findById(id)
                .orElseThrow { EntityNotFoundException("Nota fiscal com ID ${id} não encontrado.") }
            notaFiscalRepository.delete(notaFiscal)
        }catch (e: Exception){
            throw RuntimeException("Erro ao excluir nota fiscal: ${e.message}", e)
        }
    }

}