package dev.yangsijun.stomptutorial.chat.message.req

import org.bson.types.ObjectId
import java.util.*

data class ReqChatListMessage(
    val chatId: ObjectId,
    val roomId: UUID,
    val size: Int,
    val isAsc: Boolean,
)
