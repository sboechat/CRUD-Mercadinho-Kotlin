package br.com.samuel.mercado.service

import br.com.samuel.mercado.dto.ProdutoDto
import br.com.samuel.mercado.entity.Produto
import br.com.samuel.mercado.repository.ProdutoRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProdutoService (
    @Autowired private val produtoRepository: ProdutoRepository
){

    fun getProdutoById(id: UUID): ProdutoDto {
        val produto = produtoRepository.findById(id)
            .orElseThrow{ EntityNotFoundException("Produto com id: $id não encontrado.") }
        return produto.toDto()
    }

    fun getTodosProdutos(): List<ProdutoDto> {
        try {
            val produtos = produtoRepository.findAll()
            val produtosDto = produtos.map { it.toDto() }
            return produtosDto
        }catch (e: Exception){
            throw RuntimeException("Erro ao buscar todos os produto: ${e.message}", e)
        }
    }

    fun addProduto(produtoDto: ProdutoDto) {
        try{
            val produto = Produto()
            produto.nome = produtoDto.nome
            produto.codigoDeBarras = produtoDto.codigoDeBarras
            produto.valor = produtoDto.valor
            produtoRepository.save(produto)
        } catch (e: Exception) {
            throw RuntimeException("Erro ao adicionar produto: ${e.message}", e)
        }
    }

    fun updateProduto(produtoDto: ProdutoDto){
        try{
            if (produtoDto.id != null) {
                val produtoAtualizado = produtoRepository.findById(produtoDto.id)
                    .orElseThrow { EntityNotFoundException("Produto com ID ${produtoDto.id} não encontrado.") }

                produtoAtualizado.nome = produtoDto.nome
                produtoAtualizado.codigoDeBarras = produtoDto.codigoDeBarras
                produtoAtualizado.valor = produtoDto.valor
                produtoRepository.save(produtoAtualizado)
            }else{
                throw IllegalArgumentException("É necessário fornecer o ID do produto.")
            }
        } catch (e: Exception) {
            throw RuntimeException("Erro ao atualizar produto: ${e.message}", e)
        }

    }

    fun deleteProdutoById(id: UUID){
        try {
            val produto = produtoRepository.findById(id)
                .orElseThrow { EntityNotFoundException("Produto com ID ${id} não encontrado.") }
            produtoRepository.delete(produto)
        }catch (e: Exception){
            throw RuntimeException("Erro ao excluir produto: ${e.message}", e)
        }
    }
}