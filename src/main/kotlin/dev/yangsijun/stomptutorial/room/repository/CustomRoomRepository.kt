package dev.yangsijun.stomptutorial.room.repository

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.room.domain.Room
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort

interface CustomRoomRepository {
    fun findRoomsClosestToChatId(chatId: ObjectId, direction: Sort.Direction, limit: Int): List<Room>

    fun findRoomsRecent(limit: Int): List<Room>
}