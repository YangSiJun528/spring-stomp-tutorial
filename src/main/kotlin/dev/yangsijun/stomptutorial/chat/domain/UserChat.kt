package dev.yangsijun.stomptutorial.chat.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "chat")
// 방 id(=), 채팅 id(=,<>)
// - 대부분 최근 채팅을 보기 때문에 최신순 정렬, 사실 방은 (=) 조건이라 냅둠
@TypeAlias("userChat")
@CompoundIndex(def = "{'roomId': 1, 'id': -1}")
class UserChat (
    id: ObjectId,
    roomId: UUID,
    val userId: UUID,
    textContent: String,
    type: ChatType = ChatType.USER
): BaseChat(id, roomId, type, textContent)
