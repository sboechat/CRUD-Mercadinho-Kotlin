package br.com.samuel.mercado.service

import br.com.samuel.mercado.dto.ListaProdutoDto
import br.com.samuel.mercado.entity.ListaProduto
import br.com.samuel.mercado.repository.ListaProdutoRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ListaProdutoService (
    @Autowired private val listaProdutoRepository: ListaProdutoRepository
){

    fun getListaProdutoById(id: UUID): ListaProdutoDto {
        val listaProduto = listaProdutoRepository.findById(id)
            .orElseThrow{ EntityNotFoundException("Lista de produtos com id: $id não encontrado.") }
        return listaProduto.toDto()
    }

    fun addListaProduto(listaProdutoDto: ListaProdutoDto) {
        try{
            val listaProduto = ListaProduto()
            listaProduto.produto = listaProdutoDto.produto
            listaProduto.quantidadeProduto = listaProdutoDto.quantidadeProduto
            listaProduto.notaFiscal = listaProdutoDto.notaFiscal
            listaProdutoRepository.save(listaProduto)
        } catch (e: Exception) {
            throw RuntimeException("Erro ao adicionar lista de produtos: ${e.message}", e)
        }
    }

    fun updateListaProduto(listaProdutoDto: ListaProdutoDto){
        try{
            if (listaProdutoDto.id != null) {
                val listaProdutoAtualizado = listaProdutoRepository.findById(listaProdutoDto.id)
                    .orElseThrow { EntityNotFoundException("Lista de produtos com ID ${listaProdutoDto.id} não encontrado.") }

                listaProdutoAtualizado.produto = listaProdutoDto.produto
                listaProdutoAtualizado.quantidadeProduto = listaProdutoDto.quantidadeProduto
                listaProdutoAtualizado.notaFiscal = listaProdutoDto.notaFiscal
                listaProdutoRepository.save(listaProdutoAtualizado)
            }else{
                throw IllegalArgumentException("É necessário fornecer o ID da lista de produtos.")
            }
        } catch (e: Exception) {
            throw RuntimeException("Erro ao atualizar lista de produtos: ${e.message}", e)
        }

    }

    fun deleteListaProdutoById(id: UUID){
        try {
            val listaProduto = listaProdutoRepository.findById(id)
                .orElseThrow { EntityNotFoundException("ListaProduto com ID ${id} não encontrado.") }
            listaProdutoRepository.delete(listaProduto)
        }catch (e: Exception){
            throw RuntimeException("Erro ao excluir listaProduto: ${e.message}", e)
        }
    }
}