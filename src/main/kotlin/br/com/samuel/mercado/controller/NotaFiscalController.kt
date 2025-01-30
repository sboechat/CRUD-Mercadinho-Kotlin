package br.com.samuel.mercado.controller

import br.com.samuel.mercado.dto.NotaFiscalDto
import br.com.samuel.mercado.service.NotaFiscalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import java.util.UUID

@RestController
@RequestMapping("/api/notafiscal")
class NotaFiscalController(
    @Autowired private val notaFiscalService: NotaFiscalService
    ){

    @GetMapping("/{id}")
    fun getNotaFiscalById(@PathVariable id: UUID): NotaFiscalDto{
        return notaFiscalService.getNotaFiscalById(id)
    }

    @GetMapping("/todas")
    fun getTodasNotasFiscais(): List<NotaFiscalDto>{
        return notaFiscalService.getTodasNotasFiscais()
    }

    @PostMapping
    fun addNotaFiscal(@RequestBody @Valid notaFiscalDto: NotaFiscalDto): ResponseEntity<Any> {
        notaFiscalService.addNotaFiscal(notaFiscalDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun updateNotaFiscal(@RequestBody @Valid notaFiscalDto: NotaFiscalDto): ResponseEntity<Any> {
        notaFiscalService.updateNotaFiscal(notaFiscalDto)
        return ResponseEntity.ok("Nota fiscal atualizada com sucesso!")
    }

    @DeleteMapping
    fun deleteNotaFiscal(@RequestBody id: UUID): ResponseEntity<Any>{
        notaFiscalService.deleteNotaFiscalById(id)
        return ResponseEntity.ok("Nota fiscal excluída com sucesso!")
    }
}