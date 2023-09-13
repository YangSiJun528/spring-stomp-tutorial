package dev.yangsijun.stomptutorial.room.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "room")
// 특정 유저(=), 유저의 마지막으로 확인한 채팅(=,<>)
// - 대부분 최근 방을 보기 때문에 최신순 정렬, 유저id는 범위 조건을 사용하지 않기 때문에 걍 냅둠
@CompoundIndex(def = "{'userInfos.userId': 1, 'userInfos.lastViewedChatMessage': -1}")
class Room(
    @Id
    val id: UUID,
    val name: String,
    val userInfos: List<UserInfo>
) {
    companion object {
        fun create(name: String, userInfos: List<UserInfo>): Room {
            return Room(id = UUID.randomUUID(), name = name, userInfos = userInfos)
        }

        fun update(id: UUID, name: String, userInfos: List<UserInfo>): Room {
            return Room(id = UUID.randomUUID(), name = name, userInfos = userInfos)
        }
    }
}