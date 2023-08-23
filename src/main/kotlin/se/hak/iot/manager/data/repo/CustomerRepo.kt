package se.hak.iot.manager.data.repo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import se.hak.iot.manager.data.model.Customer

@Repository
interface CustomerRepo: ReactiveMongoRepository<Customer, String>, CustomCustomerRepo

interface CustomCustomerRepo {
    fun search(criteria: Criteria): Flux<Customer>
}

@Repository
class CustomCustomerRepoImpl(private val reactiveMongoTemplate: ReactiveMongoTemplate) : CustomCustomerRepo {
    override fun search(criteria: Criteria): Flux<Customer> {
        val query = Query(criteria)
        return reactiveMongoTemplate.find(query, Customer::class.java)
    }
}