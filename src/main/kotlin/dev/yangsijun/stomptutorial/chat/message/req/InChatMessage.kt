package dev.yangsijun.stomptutorial.chat.message.req

import dev.yangsijun.stomptutorial.chat.domain.ChatType
import org.bson.types.ObjectId
import java.util.*

data class InChatMessage(
    val roomId: UUID,
    val textContent: String
)
