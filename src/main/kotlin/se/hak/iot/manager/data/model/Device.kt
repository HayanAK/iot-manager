package se.hak.iot.manager.data.model

import org.springframework.data.annotation.Id
import java.util.*

enum class DeviceType {
    MCU,
    MOBILE,
    GATEWAY,
    UNKNOWN
}

enum class DeviceStatus {
    ONLINE,
    OFFLINE,
    ACTIVE,
    INACTIVE,
    UPDATE,
    ERROR,
    UNKNOWN
}

data class Device(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val status: DeviceStatus?,
    val serialNumber: String?,
    val macAddress: String?,
    val ipAddress: String?,
    val connectionProtocol: String?,
    val latitude: Double?,
    val longitude: Double?,
    val firmwareVersion: String?,
    val hardwareVersion: String?,
    val softwareVersion: String?,
    val manufacturer: String?,
    val model: String?,
    val description: String?,
    val deviceType: DeviceType?,
    val category: String?,
    val signals: List<Signal> = emptyList()
)
