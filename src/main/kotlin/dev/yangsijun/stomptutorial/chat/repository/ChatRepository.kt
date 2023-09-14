package dev.yangsijun.stomptutorial.chat.repository

import dev.yangsijun.stomptutorial.chat.domain.Chat
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface ChatRepository : CrudRepository<Chat, ObjectId>, CustomChatRepository {
}