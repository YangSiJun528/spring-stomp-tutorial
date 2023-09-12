package dev.yangsijun.stomptutorial.room.repository

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository


@Repository
class CustomMessageRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : CustomMessageRepository {
}