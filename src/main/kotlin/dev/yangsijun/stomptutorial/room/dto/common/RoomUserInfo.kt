package dev.yangsijun.stomptutorial.room.dto.common

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import java.util.*

data class RoomUserInfo(
    val userId: UUID,
    val lastViewedChat: BaseChat
)
