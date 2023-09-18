package dev.yangsijun.stomptutorial.room.message.res

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import java.util.*

data class RoomInfoMessage(
    val id: UUID,
    val name: String,
    val recentChat: BaseChat,
    val lastViewedChatId: String,
    val noCheckedCount: Int,
)
