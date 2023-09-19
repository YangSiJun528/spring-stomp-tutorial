package dev.yangsijun.stomptutorial.room.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.dto.common.RoomInfo
import dev.yangsijun.stomptutorial.room.message.res.RoomInfoMessage
import dev.yangsijun.stomptutorial.room.repository.RoomRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

// 특정 방 id를 기준 or 최신 순 기준 방 목록를 위 or 아래 n개 가져오기
@Service
class FindRoomsService(
    private val chatRepository: ChatRepository,
    private val roomRepository: RoomRepository
) {

    fun execute(userId: UUID, chatId: ObjectId, direction: Sort.Direction, limit: Int): List<Map<UUID, RoomInfoMessage>> {
        validateChatExists(chatId)

        val rooms: List<Room> = roomRepository.findRoomsClosestToChatId(userId, chatId, direction, limit)
        val roomInfos = rooms.map {
            val recentChat: List<BaseChat> = chatRepository.findAllByRoomIdOrderByIdDesc(it.id)
            RoomInfoManager.execute(userId, it, recentChat.first(), recentChat)
        }.toList()

        return roomInfos
    }

    fun execute(userId: UUID, size: Int): List<Map<UUID, RoomInfoMessage>> {
        val rooms: List<Room> = roomRepository.findRoomsRecent(userId, size)
        val roomInfos = rooms.map {
            val recentChat: List<BaseChat> = chatRepository.findAllByRoomIdOrderByIdDesc(it.id)
            RoomInfoManager.execute(userId, it, recentChat.first(), recentChat)
        }.toList()

        return roomInfos
    }

    private fun validateChatExists(chatId: ObjectId) {
        if (!chatRepository.existsById(chatId))
            throw RuntimeException("Can't find Chat by input ID : $chatId")
    }
}