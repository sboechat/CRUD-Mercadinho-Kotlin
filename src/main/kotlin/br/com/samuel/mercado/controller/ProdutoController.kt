package br.com.samuel.mercado.controller

import br.com.samuel.mercado.dto.ProdutoDto
import br.com.samuel.mercado.service.ProdutoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import java.util.UUID

@RestController
@RequestMapping("/api/produto")
class ProdutoController(
    @Autowired private val produtoService: ProdutoService
    ){

    @GetMapping("/{id}")
    fun getProdutoById(@PathVariable id: UUID): ProdutoDto{
        return produtoService.getProdutoById(id)
    }

    @GetMapping("/todos")
    fun getTodosProdutos(): List<ProdutoDto>{
        return produtoService.getTodosProdutos()
    }

    @PostMapping
    fun addProduto(@RequestBody @Valid produtoDto: ProdutoDto): ResponseEntity<Any> {
        produtoService.addProduto(produtoDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun updateProduto(@RequestBody @Valid produtoDto: ProdutoDto): ResponseEntity<Any> {
        produtoService.updateProduto(produtoDto)
        return ResponseEntity.ok("Produto atualizado com sucesso!")
    }

    @DeleteMapping
    fun deleteProduto(@RequestBody id: UUID): ResponseEntity<Any>{
        produtoService.deleteProdutoById(id)
        return ResponseEntity.ok("Produto excluído com sucesso!")
    }

}