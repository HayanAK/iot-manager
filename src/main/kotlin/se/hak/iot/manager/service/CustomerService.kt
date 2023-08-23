package se.hak.iot.manager.service

import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import se.hak.iot.manager.data.model.Customer
import se.hak.iot.manager.data.repo.CustomerRepo

@Service
class CustomerService (private val repo: CustomerRepo): BaseService<Customer, String>(repo) {
    override fun search(params: Map<String, String>): Flux<Customer> {
        val criteria = Criteria()

        // ".*it.*" behaves like "LIKE" in sql it will search for substring.
        // "i" is for case-insensitivity
        params["name"]?.let {
            criteria.orOperator(Criteria.where("name").regex(".*$it.*", "i"))
        }
        val res = repo.search(criteria)
        return res
    }

}