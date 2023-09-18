package dev.yangsijun.stomptutorial.room.domain

import org.bson.types.ObjectId
import java.util.UUID

data class UserInfo(
    val userId: UUID,
    val lastViewedChatId: ObjectId
)