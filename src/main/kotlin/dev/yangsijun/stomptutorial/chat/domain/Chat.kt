package dev.yangsijun.stomptutorial.chat.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "chat")
// 방 id(=), 채팅 id(=,<>)
// - 대부분 최근 채팅을 보기 때문에 최신순 정렬, 사실 방은 (=) 조건이라 냅둠
@CompoundIndex(def = "{'roomId': 1, 'id': -1}")
class Chat private constructor(
    @Id
    val id: ObjectId,
    val roomId: UUID,
    val userId: UUID,
    val type: ChatType,
    val textContent: String
) {
    companion object {
        // DB 저장하기 직전에 사용 - ObjectId에 시간 값이 저장되기 때문
        fun create(roomId: UUID, userId: UUID, type: ChatType, textContent: String): Chat {
            return Chat(id = ObjectId.get(), roomId = roomId, userId = userId, type = type, textContent = textContent)
        }

        fun update(id: ObjectId, userId: UUID, roomId: UUID, type: ChatType, textContent: String): Chat {
            return Chat(id = id, roomId = roomId, userId = userId, type = type, textContent = textContent)
        }
    }
}
