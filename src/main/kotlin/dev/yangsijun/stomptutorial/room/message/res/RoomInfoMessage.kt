package dev.yangsijun.stomptutorial.room.message.res

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import java.util.*

data class RoomInfoMessage(
    val roomId: UUID,
    val roomName: String,
    val recentChat: BaseChat,
    val lastViewedChatId: String,
    val noCheckedCount: Int
)
