package dev.yangsijun.stomptutorial.chat.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.message.res.OutChatMessage
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.repository.RoomRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

// 채팅 목록 돌려줌
@Service
class FindChatsService(
    private val chatRepository: ChatRepository,
    private val roomRepository: RoomRepository
) {

    fun execute(chatId: ObjectId, roomId: UUID, searchPrevious: Boolean, size: Int): List<OutChatMessage> {
        validateRoomExists(roomId)
        validateChatExists(chatId)

        val chats: List<BaseChat> = chatRepository.findChatsClosestToId(
            chatId = chatId,
            roomId = roomId,
            direction = booleanToDirection(searchPrevious),
            limit = size
        )

        return chats.map { chatToChatMessage(it) }
    }

    fun execute(roomId: UUID, size: Int): List<OutChatMessage> {
        validateRoomExists(roomId)

        val chats: List<BaseChat> = chatRepository.findChatsRecent(
            roomId = roomId,
            limit = size
        )

        return chats.map { chatToChatMessage(it) }
    }

    private fun validateRoomExists(roomId: UUID) {
        if (!roomRepository.existsById(roomId))
            throw RuntimeException("Can't find Room by input ID : $roomId")
    }

    private fun validateChatExists(chatId: ObjectId) {
        if (!chatRepository.existsById(chatId))
            throw RuntimeException("Can't find Chat by input ID : $chatId")
    }

    private fun booleanToDirection(b: Boolean): Sort.Direction = if (b) Sort.Direction.ASC else Sort.Direction.DESC

    private fun chatToChatMessage(chat: BaseChat): OutChatMessage {
        return OutChatMessage(
            chatId = chat.id,
            roomId = chat.roomId,
            type = chat.type,
            textContent = chat.textContent
        )
    }
}
