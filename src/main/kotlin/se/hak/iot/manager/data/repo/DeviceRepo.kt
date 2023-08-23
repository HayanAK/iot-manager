package se.hak.iot.manager.data.repo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import se.hak.iot.manager.data.model.Device

@Repository
interface DeviceRepo : ReactiveMongoRepository<Device, String>, CustomDeviceRepo

interface CustomDeviceRepo {
    fun search(criteria: Criteria): Flux<Device>
}

@Repository
class CustomDeviceRepoImpl(private val reactiveMongoTemplate: ReactiveMongoTemplate) : CustomDeviceRepo {
    override fun search(criteria: Criteria): Flux<Device> {
        val query = Query(criteria)
        return reactiveMongoTemplate.find(query, Device::class.java)
    }
}