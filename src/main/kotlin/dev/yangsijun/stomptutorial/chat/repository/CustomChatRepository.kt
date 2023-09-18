package dev.yangsijun.stomptutorial.chat.repository

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import org.bson.types.ObjectId
import java.util.*

// 기능 or 역할 단위로 더 분리할 수도 있음
interface CustomChatRepository {
    fun findChatsClosestToId(chatId: ObjectId, roomId: UUID, direction: Int, limit: Int): List<BaseChat>

    fun findChatsRecent(roomId: UUID, limit: Int): List<BaseChat>
}