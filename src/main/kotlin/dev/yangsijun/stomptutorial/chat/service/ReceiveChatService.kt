package dev.yangsijun.stomptutorial.chat.service

import dev.yangsijun.stomptutorial.chat.domain.UserChat
import dev.yangsijun.stomptutorial.chat.message.req.InChatMessage
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
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
        validateRoomExists(message.roomId)
        val newChat = UserChat(
            id = ObjectId.get(),
            userId = userId,
            roomId = message.roomId,
            textContent = message.textContent
        )

        chatRepository.save(newChat)

        //TODO send EVENT
    }

    private fun validateRoomExists(roomId: UUID) {
        if (!roomRepository.existsById(roomId))
            throw RuntimeException("Can't find Room by input ID : $roomId")
    }
}