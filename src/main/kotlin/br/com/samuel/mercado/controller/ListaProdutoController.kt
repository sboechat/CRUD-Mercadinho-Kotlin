package br.com.samuel.mercado.controller

import br.com.samuel.mercado.dto.ListaProdutoDto
import br.com.samuel.mercado.service.ListaProdutoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import java.util.UUID

@RestController
@RequestMapping("/api/listaProduto")
class ListaProdutoController(
    @Autowired private val listaProdutoService: ListaProdutoService
    ){

    @GetMapping("/{id}")
    fun getListaProdutoById(@PathVariable id: UUID): ListaProdutoDto{
        return listaProdutoService.getListaProdutoById(id)
    }

    @PostMapping
    fun addListaProduto(@RequestBody @Valid listaProdutoDto: ListaProdutoDto): ResponseEntity<Any> {
        listaProdutoService.addListaProduto(listaProdutoDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun updateListaProduto(@RequestBody @Valid listaProdutoDto: ListaProdutoDto): ResponseEntity<Any> {
        listaProdutoService.updateListaProduto(listaProdutoDto)
        return ResponseEntity.ok("Lista de produtos atualizado com sucesso!")
    }

    @DeleteMapping
    fun deleteListaProduto(@RequestBody id: UUID): ResponseEntity<Any>{
        listaProdutoService.deleteListaProdutoById(id)
        return ResponseEntity.ok("Lista de produtos excluído com sucesso!")
    }

}