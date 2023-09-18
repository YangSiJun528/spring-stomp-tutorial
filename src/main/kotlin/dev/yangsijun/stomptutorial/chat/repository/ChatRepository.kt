package dev.yangsijun.stomptutorial.chat.repository

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface ChatRepository : CrudRepository<BaseChat, ObjectId>, CustomChatRepository {
}