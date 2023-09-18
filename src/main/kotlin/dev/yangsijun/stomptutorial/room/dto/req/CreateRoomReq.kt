package dev.yangsijun.stomptutorial.room.dto.req

import org.bson.types.ObjectId
import java.util.UUID

data class CreateRoomReq(
    val creatorId: UUID,
    val memberIds: List<UUID>
)