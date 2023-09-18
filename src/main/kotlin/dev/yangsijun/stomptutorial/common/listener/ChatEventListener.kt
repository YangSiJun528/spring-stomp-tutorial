package dev.yangsijun.stomptutorial.common.listener

import dev.yangsijun.stomptutorial.common.event.SavedNewChatEvent
import dev.yangsijun.stomptutorial.room.service.RefreshRoomService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ChatEventListener(
    private val refreshRoomService: RefreshRoomService
) {
    @EventListener
    fun savedNewChatEvent(event: SavedNewChatEvent) {
        refreshRoomService.execute()
    }
}