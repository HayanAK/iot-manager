package se.hak.iot.manager.data.repo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import se.hak.iot.manager.data.model.User

@Repository
interface UserRepo: ReactiveMongoRepository<User, String>, CustomUserRepo

interface CustomUserRepo {
    fun search(criteria: Criteria): Flux<User>
}

@Repository
class CustomUserRepoImpl(private val reactiveMongoTemplate: ReactiveMongoTemplate) : CustomUserRepo {
    override fun search(criteria: Criteria): Flux<User> {
        val query = Query(criteria)
        return reactiveMongoTemplate.find(query, User::class.java)
    }
}