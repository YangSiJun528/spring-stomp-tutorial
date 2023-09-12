package dev.yangsijun.stomptutorial.room

import dev.yangsijun.stomptutorial.message.ChatMessage
import java.util.UUID

data class UserInfo(
    val userId: UUID,
    val lastViewedChatMessage: ChatMessage
)