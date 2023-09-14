package dev.yangsijun.stomptutorial.room.domain

import dev.yangsijun.stomptutorial.chat.domain.Chat
import org.bson.types.ObjectId
import java.util.UUID

data class UserInfo(
    val userId: UUID,
    val lastViewedChatId: ObjectId
)