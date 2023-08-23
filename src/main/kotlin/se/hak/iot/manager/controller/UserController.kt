package se.hak.iot.manager.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import se.hak.iot.manager.data.model.User
import se.hak.iot.manager.service.UserService

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping("")
    fun all(): Flux<User> {
        return userService.all()
    }

    @PostMapping("")
    fun create(user: User): Mono<User> {
        return userService.addOrUpdate(user)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: String): Mono<User> {
        return userService.getOne(id)
    }
}