package dev.yangsijun.stomptutorial.chat.message.req

import org.bson.types.ObjectId

data class ReqChatListMessage(
    val chatId: ObjectId,
    val size: Int,
    val isAsc: Boolean,
)
