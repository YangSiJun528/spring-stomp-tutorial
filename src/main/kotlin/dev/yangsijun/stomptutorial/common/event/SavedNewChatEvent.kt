package dev.yangsijun.stomptutorial.common.event

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.room.domain.Room

data class SavedNewChatEvent(
    val room: Room,
    val newChat: BaseChat
)
