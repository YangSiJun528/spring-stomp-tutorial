package dev.yangsijun.stomptutorial.room.dto.common

import dev.yangsijun.stomptutorial.room.message.res.RoomInfoMessage
import java.util.*

data class RoomInfoDto(
    val userId: UUID,
    val roomInfo: RoomInfoMessage
)
