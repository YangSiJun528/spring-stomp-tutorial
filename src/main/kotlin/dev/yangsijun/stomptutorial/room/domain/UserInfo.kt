package dev.yangsijun.stomptutorial.room.domain

import dev.yangsijun.stomptutorial.message.domain.Chat
import java.util.UUID

data class UserInfo(
    val userId: UUID,
    val lastViewedChat: Chat
)