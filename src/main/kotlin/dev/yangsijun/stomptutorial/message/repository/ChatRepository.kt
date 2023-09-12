package dev.yangsijun.stomptutorial.message.repository

import dev.yangsijun.stomptutorial.message.domain.Chat
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface ChatRepository : CrudRepository<Chat, ObjectId>, CustomChatRepository {
}