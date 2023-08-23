package se.hak.iot.manager.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.util.*

@Document
data class Customer(
    @Id
    val id: String = UUID.randomUUID().toString(),
    @Indexed(unique = true)
    val name: String,
    val address: String? = null,
    val description: String? = null,
    @DocumentReference(lazy = true)
    val users: List<User>? = emptyList(),
    @DocumentReference
    val areas: List<Area>? = emptyList(),
    @DocumentReference(lazy = true)
    val parentCustomer: Customer? = null,
    @DocumentReference(lazy = true)
    val childCustomers: List<Customer>? = emptyList()
)