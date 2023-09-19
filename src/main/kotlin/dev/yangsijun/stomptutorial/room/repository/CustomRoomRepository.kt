package dev.yangsijun.stomptutorial.room.repository

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.room.domain.Room
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import java.util.*

interface CustomRoomRepository {
    fun findRoomsClosestToChatId(userId: UUID, chatId: ObjectId, direction: Sort.Direction, limit: Int): List<Room>

    fun findRoomsRecent(userId: UUID, limit: Int): List<Room>
}