package se.hak.iot.manager.data.repo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import se.hak.iot.manager.data.model.Area

@Repository
interface AreaRepo : ReactiveMongoRepository<Area, String>, CustomAreaRepo {
}

interface CustomAreaRepo {
    fun search(criteria: Criteria): Flux<Area>
}

@Repository
class CustomAreaRepoImpl(private val reactiveMongoTemplate: ReactiveMongoTemplate) : CustomAreaRepo {
    override fun search(criteria: Criteria): Flux<Area> {
        val query = Query(criteria)
        return reactiveMongoTemplate.find(query, Area::class.java)
    }
}

