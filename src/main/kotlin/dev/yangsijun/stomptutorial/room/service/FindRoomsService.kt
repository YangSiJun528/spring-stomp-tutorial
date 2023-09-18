package dev.yangsijun.stomptutorial.room.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.dto.common.RoomInfo
import dev.yangsijun.stomptutorial.room.repository.RoomRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import java.lang.RuntimeException
import java.util.*

// 특정 방 id를 기준으로 or 최근 데이터를 기준으로 위 or 아래 n개 가져오기
class FindRoomsService(
    private val chatRepository: ChatRepository,
    private val roomRepository: RoomRepository
) {

    fun execute(chatId: ObjectId, direction: Sort.Direction, limit: Int): List<RoomInfo> {
        validateChatExists(chatId)

        val rooms: List<Room> = roomRepository.findRoomsClosestToChatId(chatId, direction, limit)
        val roomInfos: List<RoomInfo> = rooms.map {
            val recentChat: BaseChat = chatRepository.findFirstByRoomIdOrderByIdDesc(it.id)
            RoomInfo(it.id, recentChat, it.userInfos)
        }.toList()
        return roomInfos
    }

    fun execute(size: Int): List<RoomInfo> {
        val rooms: List<Room> = roomRepository.findRoomsRecent(size)
        val roomInfos: List<RoomInfo> = rooms.map {
            val recentChat: BaseChat = chatRepository.findFirstByRoomIdOrderByIdDesc(it.id)
            RoomInfo(it.id, recentChat, it.userInfos)
        }.toList()
        return roomInfos
    }

    private fun validateChatExists(chatId: ObjectId) {
        if (!chatRepository.existsById(chatId))
            throw RuntimeException("Can't find Chat by input ID : $chatId")
    }
}