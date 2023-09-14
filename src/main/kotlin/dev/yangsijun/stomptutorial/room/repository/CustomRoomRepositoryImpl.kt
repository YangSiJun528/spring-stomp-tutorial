package dev.yangsijun.stomptutorial.room.repository

import dev.yangsijun.stomptutorial.chat.repository.CustomChatRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository


@Repository
class CustomRoomRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : CustomChatRepository {
}