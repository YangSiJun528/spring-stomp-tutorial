package dev.yangsijun.stomptutorial.chat.message.res

import java.util.*

data class RoomInfoMessage(
    val id: UUID,
    val name: String,
    val lastViewedChatContent: String,
    val noCheckedCount: Int,
)
