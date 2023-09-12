package dev.yangsijun.stomptutorial.message.repository

import dev.yangsijun.stomptutorial.message.domain.Message
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, ObjectId>, CustomMessageRepository {
}