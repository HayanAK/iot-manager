package se.hak.iot.manager

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import se.hak.iot.manager.data.model.Customer
import se.hak.iot.manager.data.repo.CustomerRepo
import se.hak.iot.manager.service.CustomerService

@ExtendWith(SpringExtension::class)
//@TestPropertySource(locations= ["classpath:application-test.properties"])
@DataMongoTest
class ManagerApplicationTests() {


    @Autowired
    lateinit var customerRepo: CustomerRepo
    lateinit var customerService: CustomerService

    @Test
    fun contextLoads() {
    }

    @BeforeEach
    fun setup() {
        customerService = CustomerService(customerRepo)
    }

    @Test
    @Order(2)
    fun addCustomerSearchAndDelete() {
        customerService.addOrUpdate(Customer(name = "Foo"))
            .thenMany(customerService.search(params = mapOf("name" to "fo")))
            .doOnNext { item -> assert(item.name == "Foo") }
            .doOnNext { item -> customerService.deleteById(item.id).subscribe() }
            .blockLast()
    }
}
