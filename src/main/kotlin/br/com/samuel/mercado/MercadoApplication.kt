package br.com.samuel.mercado

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MercadoApplication

fun main(args: Array<String>) {
	runApplication<MercadoApplication>(*args)
}
