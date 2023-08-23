package se.hak.iot.manager.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.util.*


enum class AreaType {
    FACTORY,
    HOUSE,
    BOAT,
    CAR,
    PERSONAL,
    ROOT
}

// The area is a physical location where devices are connected.
@Document
data class Area(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val address: String? = null,
    val latitude: Double?,
    val longitude: Double? = null,
    val type: AreaType? = null,
    val description: String? = null,
    @DocumentReference(lazy = true)
    val parentArea: Area? = null,
    @DocumentReference(lazy = true)
    val childAreas: List<Area>? = arrayListOf(),
    val devices: List<Device>? = arrayListOf()
)
