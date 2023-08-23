package se.hak.iot.manager.service

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

abstract class BaseService<T: Any, ID: Any>(private val repo: ReactiveMongoRepository<T, ID>){
    fun all(): Flux<T> {
        return repo.findAll()
    }

    fun getOne(id: ID): Mono<T> {
        return repo.findById(id)
    }

    fun addOrUpdate(entity: T): Mono<T> {
        return repo.save(entity)
    }

    fun delete(entity: T): Mono<Void> {
        return repo.delete(entity)
    }

    fun deleteById(id: ID): Mono<Void> {
        return repo.deleteById(id)
    }

    abstract fun search(params: Map<String, String>): Flux<T>
}