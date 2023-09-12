package dev.yangsijun.stomptutorial.room.controller
// package com.gsmNetworking.chat

import dev.yangsijun.stomptutorial.message.dto.websocket.ChatMessage
import dev.yangsijun.stomptutorial.room.domain.Room
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/room/v1")
class RoomRestController {

    @MessageMapping("/by-user-id/{userId}")
    fun getRooms(
        @PathVariable("userId") userId: UUID,
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int
    ): List<Room> {
        // TODO 메서드 이름 생각해보기
        //  최신 채팅 방 n개 가져오는 역할임
        return listOf()
    }

}