package se.hak.iot.manager.service

import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import se.hak.iot.manager.data.model.User
import se.hak.iot.manager.data.repo.UserRepo

@Service
class UserService(private val repo: UserRepo): BaseService<User, String>(repo) {
    override fun search(params: Map<String, String>): Flux<User> {
        val criteria = Criteria()

        // ".*it.*" behaves like "LIKE" in sql it will search for substring.
        // "i" is for case-insensitivity
        params["name"]?.let {
            criteria.orOperator(Criteria.where("name").regex(".*$it.*", "i"))
        }

        params["email"]?.let {
            criteria.orOperator(Criteria.where("email").regex(".*$it.*", "i"))
        }

        return repo.search(criteria)
    }
}