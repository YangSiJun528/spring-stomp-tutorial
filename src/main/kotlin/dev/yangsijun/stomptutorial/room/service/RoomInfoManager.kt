package dev.yangsijun.stomptutorial.room.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.domain.UserInfo
import dev.yangsijun.stomptutorial.room.message.res.RoomInfoMessage
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*


// 이름 바꾸기 방 목록 만들어주는 객체임
class RoomInfoManager {

    companion object {
        fun execute(room: Room, newChat: BaseChat, chats: List<BaseChat>): Map<UUID, RoomInfoMessage> {

            val rs: Map<UUID, RoomInfoMessage> = room.userInfos.associateBy(
                { it.userId },
                { toRoomInfoMessage(chats, it, room, newChat) }
            )

            return rs
        }

        fun execute(userId: UUID, room: Room, newChat: BaseChat, chats: List<BaseChat>): Map<UUID, RoomInfoMessage> {

            val rs: Map<UUID, RoomInfoMessage> = room.userInfos
                .filter { userInfo ->
                    userInfo.userId == userId
                }
                .associateBy(
                    { it.userId },
                    { toRoomInfoMessage(chats, it, room, newChat) }
                )

            return rs
        }

        private fun toRoomInfoMessage(
            chats: List<BaseChat>, userInfo: UserInfo, room: Room, newChat: BaseChat
        ): RoomInfoMessage {
            return RoomInfoMessage(
                roomId = room.id,
                roomName = room.name,
                recentChat = newChat,
                lastViewedChatId = userInfo.lastViewedChatId.toString(),
                noCheckedCount = findNoCheckChatCount(chats, userInfo.lastViewedChatId)
            )
        }

        private fun findNoCheckChatCount(chats: List<BaseChat>, lastViewedChatId: ObjectId): Int {
            var unseenChatCount = 0
            for (chat in chats) {
                if (chat.id == lastViewedChatId) {
                    break
                }
                unseenChatCount++
            }
            return unseenChatCount
        }
    }
}