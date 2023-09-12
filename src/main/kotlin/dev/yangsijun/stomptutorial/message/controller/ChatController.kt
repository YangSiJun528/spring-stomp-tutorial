package dev.yangsijun.stomptutorial.message.controller

import dev.yangsijun.stomptutorial.message.domain.Chat
import org.bson.types.ObjectId
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat/v1")
class ChatController {

    @MessageMapping("/by-id/{chatId}")
    fun getChats(
        @PathVariable("messageId") chatId: ObjectId,
        @RequestParam(name = "size") size: Int,
        @RequestParam(name = "isAsc") isAsc: Boolean
    ): List<Chat> {
        // getMessage
        return listOf()
    }

}