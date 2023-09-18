package dev.yangsijun.stomptutorial.chat.repository

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ChatRepository : CrudRepository<BaseChat, ObjectId>, CustomChatRepository {
    fun findFirstByRoomIdOrderByIdDesc(roomId: UUID): BaseChat
    fun findAllByRoomIdOrderByIdDesc(roomId: UUID, pageable: Pageable = Pageable.ofSize(100)): List<BaseChat>
}