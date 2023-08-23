package se.hak.iot.manager.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

enum class UserType {
    ADMIN,
    DEVELOPER,
    CUSTOMER_ADMIN,
    CUSTOMER_DEVELOPER,
    USER,
    GUEST
}

@Document
data class User(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    @Indexed(unique = true)
    val email: String,
    val password: String,
    val address: String?,
    val type: UserType?,
    val description: String?
)
