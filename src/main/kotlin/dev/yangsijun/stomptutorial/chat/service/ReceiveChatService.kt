package dev.yangsijun.stomptutorial.chat.service

import dev.yangsijun.stomptutorial.chat.domain.UserChat
import dev.yangsijun.stomptutorial.chat.message.req.InChatMessage
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.common.event.SavedNewChatEvent
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.repository.RoomRepository
import org.bson.types.ObjectId
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.util.*

// 채팅 받고 저장 역할 - 저장하면 이벤트(TransactionalEvent)도 보냄

@Service
class ReceiveChatService(
    private val chatRepository: ChatRepository,
    private val roomRepository: RoomRepository,
    private val eventPublisher: ApplicationEventPublisher
) {
    @Transactional(rollbackFor = [Exception::class])
    fun execute(userId: UUID, message: InChatMessage) {
        val room: Room = roomRepository.findById(message.roomId)
            .orElseThrow { RuntimeException("Can't find Room by input ID : $message.roomId") }
        val newChat = UserChat(
            id = ObjectId.get(),
            userId = userId,
            roomId = message.roomId,
            textContent = message.textContent
        )

        chatRepository.save(newChat)

        eventPublisher.publishEvent(SavedNewChatEvent(room, newChat))
    }
}