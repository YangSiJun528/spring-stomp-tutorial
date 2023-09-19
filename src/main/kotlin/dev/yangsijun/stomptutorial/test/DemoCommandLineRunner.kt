package dev.yangsijun.stomptutorial.test

import dev.yangsijun.stomptutorial.chat.message.req.InChatMessage
import dev.yangsijun.stomptutorial.chat.service.FindChatsService
import dev.yangsijun.stomptutorial.chat.service.ReceiveChatService
import dev.yangsijun.stomptutorial.room.dto.req.CreateRoomReq
import dev.yangsijun.stomptutorial.room.service.CreateRoomService
import dev.yangsijun.stomptutorial.room.service.FindRoomsService
import dev.yangsijun.stomptutorial.room.service.RefreshRoomService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class DemoCommandLineRunner(
    private val findChatsService: FindChatsService,
    private val receiveChatService: ReceiveChatService,
    private val createRoomService: CreateRoomService,
    private val findRoomsService: FindRoomsService,
    private val refreshRoomService: RefreshRoomService
): CommandLineRunner {
    override fun run(vararg args: String?) {
        val userId1 = UUID.fromString("550e8400-e29b-41d4-a716-446655440000")
        val userId2 = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
        val userId3 = UUID.fromString("6ba7b810-9dad-11d1-80b4-00c04fd430c8")
        val userId4 = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
        val userId5 = UUID.fromString("550e8400-e29b-41d4-a716-446655440001")
        val userId6 = UUID.fromString("123e4567-e89b-12d3-a456-426614174001")

        val roomId1 = UUID.fromString("360471aa-fb1c-4506-8485-88291ae1e3ed")
        val roomId2 = UUID.fromString("360471aa-fb1c-4506-8485-446655440001")

        createRoomService.execute(CreateRoomReq(userId1, mutableListOf(userId1, userId2)), roomId1)

        val rooms = findRoomsService.execute(userId2, 10)
        val room = rooms.get(0)

        val chats1 = findChatsService.execute(roomId1, 10)
        val chat1 = chats1.get(0)

        createRoomService.execute(CreateRoomReq(userId1, mutableListOf(userId1, userId3)), roomId2)

        receiveChatService.execute(userId1, InChatMessage(roomId2, "userId1이 보내는 메시지"))
        receiveChatService.execute(userId3, InChatMessage(roomId2, "userId3이 보내는 메시지"))

        val chats2 = findChatsService.execute(roomId2, 10)
        val chat2 = chats2.get(1)

        val rooms2 = findRoomsService.execute(userId3, 10)
        val room2 = rooms.get(0)
    }
}