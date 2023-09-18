package dev.yangsijun.stomptutorial.common.listener

import dev.yangsijun.stomptutorial.common.event.SavedNewChatEvent
import dev.yangsijun.stomptutorial.room.service.RefreshRoomService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ChatEventListener(
    private val refreshRoomService: RefreshRoomService
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun savedNewChatEvent(event: SavedNewChatEvent) {
        refreshRoomService.execute(event.room, event.newChat)
    }
}