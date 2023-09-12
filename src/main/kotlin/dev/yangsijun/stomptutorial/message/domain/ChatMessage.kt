package dev.yangsijun.stomptutorial.message.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "chat")
class ChatMessage(
    @Id
    val id: ObjectId,
    val roomId: UUID,
    val textContent: String
)
