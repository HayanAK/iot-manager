package se.hak.iot.manager.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import se.hak.iot.manager.data.model.Customer
import se.hak.iot.manager.service.CustomerService

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("")
    fun all(): Flux<Customer> {
        return customerService.all()
    }

    @PostMapping("")
    fun create(customer: Customer): Mono<Customer> {
        return customerService.addOrUpdate(customer)
    }


    @GetMapping("/{id}")
    fun getOne(@PathVariable id: String): Mono<Customer> {
        return customerService.getOne(id)
    }
}