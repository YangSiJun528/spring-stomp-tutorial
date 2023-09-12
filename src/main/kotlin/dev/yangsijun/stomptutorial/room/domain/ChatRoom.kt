package dev.yangsijun.stomptutorial.room.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "room")
class ChatRoom(
    @Id
    val id: UUID,
    val userInfos: List<UserInfo>
)