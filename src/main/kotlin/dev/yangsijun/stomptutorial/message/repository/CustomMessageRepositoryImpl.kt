package dev.yangsijun.stomptutorial.message.repository

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository


@Repository
class CustomMessageRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : CustomMessageRepository {
}