package dev.yangsijun.stomptutorial.room.repository

import dev.yangsijun.stomptutorial.message.domain.ChatMessage
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<ChatMessage, ObjectId>, CustomMessageRepository {
}