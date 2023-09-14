package dev.yangsijun.stomptutorial.chat.message.req

import java.util.*

data class ReqRoomListMessage(
    val roomId: UUID,
    val size: Int,
    val isAsc: Boolean,
)
