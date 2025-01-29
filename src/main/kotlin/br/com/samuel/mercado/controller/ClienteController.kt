package br.com.samuel.mercado.controller

import br.com.samuel.mercado.dto.ClienteDto
import br.com.samuel.mercado.service.ClienteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import java.util.UUID

@RestController
@RequestMapping("/api/cliente")
class ClienteController(
    @Autowired private val clienteService: ClienteService
    ){

    @GetMapping("/{cpf}")
    fun getClienteByCpf(@PathVariable cpf: String): ClienteDto{
        return clienteService.getClienteByCpf(cpf)
    }

    @GetMapping("/{id}")
    fun getClienteById(@PathVariable id: UUID): ClienteDto{
        return clienteService.getClienteById(id)
    }

    @GetMapping("/todos")
    fun getTodosClientes(): List<ClienteDto>{
        return clienteService.getTodosClientes()
    }

    @PostMapping
    fun addCliente(@RequestBody @Valid clienteDto: ClienteDto): ResponseEntity<Any> {
        clienteService.addCliente(clienteDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun updateCliente(@RequestBody @Valid clienteDto: ClienteDto): ResponseEntity<Any> {
        clienteService.updateCliente(clienteDto)
        return ResponseEntity.ok("Cliente atualizado com sucesso!")
    }

    @DeleteMapping
    fun deleteCliente(@RequestBody id: UUID): ResponseEntity<Any>{
        clienteService.deleteClienteById(id)
        return ResponseEntity.ok("Cliente excluído com sucesso!")
    }

}