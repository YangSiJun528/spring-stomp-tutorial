package dev.yangsijun.stomptutorial.room.dto.req

import org.bson.types.ObjectId

data class CreateRoomReq(
    val creatorId: ObjectId,
    val memberIds: List<ObjectId>
)