package dev.yangsijun.stomptutorial.room.controller

import dev.yangsijun.stomptutorial.message.dto.websocket.ChatMessage
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/room/v1")
class RoomController {

    @MessageMapping("/{roomId}")
    @SendTo("/sub/room/v1/{roomId}")
    fun greeting(@DestinationVariable roomId:UUID, message: ChatMessage): ChatMessage {
        // TODO Save Chat
        return message
    }
}