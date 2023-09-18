package dev.yangsijun.stomptutorial.room.sender

import org.springframework.messaging.simp.SimpMessagingTemplate

class RoomSender(
    private val template: SimpMessagingTemplate
) {
}