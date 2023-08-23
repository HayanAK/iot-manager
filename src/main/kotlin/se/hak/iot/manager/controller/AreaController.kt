package se.hak.iot.manager.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import se.hak.iot.manager.data.model.Area
import se.hak.iot.manager.service.AreaService

@RestController
@RequestMapping("api/areas")
@Tag(name = "Areas", description = "Manage the areas")
class AreaController(private val areaService: AreaService) {

    private val log = LoggerFactory.getLogger(AreaController::class.java)

    @GetMapping("")
    @Operation(summary = "Get all areas")
    fun all(): Flux<Area> {
        return areaService.all().onErrorResume { e ->
            log.error("Error getting all areas", e)
            Mono.empty()
        }
    }

    @PostMapping("")
    @Operation(summary = "Create an area")
    fun create(area: Area): Mono<Area> {
        return areaService.addOrUpdate(area).onErrorResume { e ->
            log.error("Error creating area: $area", e)
            Mono.empty()
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an area by id")
    fun getOne(@PathVariable id: String): Mono<ResponseEntity<Area>> {
        return areaService.getOne(id).map { ResponseEntity.ok(it) }.defaultIfEmpty(ResponseEntity.notFound().build())
            .onErrorResume { e ->
                log.error("Error getting area with id: $id", e)
                Mono.just(ResponseEntity.internalServerError().build())
            }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an area by id")
    fun delete(@PathVariable id: String): Mono<ResponseEntity<Void>> {
        return areaService.getOne(id).flatMap { area ->
            areaService.delete(area).then(Mono.just(ResponseEntity<Void>(HttpStatus.OK)))
        }.defaultIfEmpty(ResponseEntity.notFound().build()).onErrorResume { e ->
            log.error("Error deleting area with id: $id", e)
            Mono.just(ResponseEntity.internalServerError().build())
        }
    }
}