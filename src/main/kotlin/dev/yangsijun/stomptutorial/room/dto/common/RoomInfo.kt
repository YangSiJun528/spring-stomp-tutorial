package dev.yangsijun.stomptutorial.room.dto.common

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.room.domain.UserInfo
import java.util.UUID

data class RoomInfo(
    val roomId: UUID,
    val recentChat: BaseChat,
    val userInfos: List<UserInfo>
)
