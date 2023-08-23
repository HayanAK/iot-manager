package se.hak.iot.manager.service

import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import se.hak.iot.manager.data.model.Device
import se.hak.iot.manager.data.repo.DeviceRepo

@Service
class DeviceService (private val repo: DeviceRepo): BaseService<Device, String>(repo) {
    override fun search(params: Map<String, String>): Flux<Device> {
        val criteria = Criteria()

        // ".*it.*" behaves like "LIKE" in sql it will search for substring.
        // "i" is for case-insensitivity
        params["name"]?.let {
            criteria.orOperator(Criteria.where("name").regex(".*$it.*", "i"))
        }
        params["serialNumber"]?.let {
            criteria.orOperator(Criteria.where("serialNumber").regex(".*$it.*", "i"))
        }
        params["manufacturer"]?.let {
            criteria.orOperator(Criteria.where("manufacturer").regex(".*$it.*", "i"))
        }
        params["model"]?.let {
            criteria.orOperator(Criteria.where("model").regex(".*$it.*", "i"))
        }
        params["description"]?.let {
            criteria.orOperator(Criteria.where("model").regex(".*$it.*", "i"))
        }

        return repo.search(criteria)
    }
}