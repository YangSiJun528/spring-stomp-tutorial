package dev.yangsijun.stomptutorial.chat.message.res

import dev.yangsijun.stomptutorial.chat.domain.ChatType
import org.bson.types.ObjectId
import java.util.*

data class OutChatMessage(
    val chatId: ObjectId,
    val roomId: UUID,
    val type: ChatType,
    val textContent: String
)
