package se.hak.iot.manager.data.model

import java.time.LocalDateTime

data class Signal(val topic: String, val payload: String, val ts: LocalDateTime)
