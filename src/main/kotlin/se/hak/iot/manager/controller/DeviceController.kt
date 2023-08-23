package se.hak.iot.manager.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import se.hak.iot.manager.data.model.Device
import se.hak.iot.manager.service.DeviceService

@RestController
@RequestMapping("api/devices")
class DeviceController(private val deviceService: DeviceService) {
    @GetMapping("")
    fun all(): Flux<Device> {
        return deviceService.all()
    }

    @PostMapping("")
    fun create(device: Device): Mono<Device> {
        return deviceService.addOrUpdate(device)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: String): Mono<Device> {
        return deviceService.getOne(id)
    }

}