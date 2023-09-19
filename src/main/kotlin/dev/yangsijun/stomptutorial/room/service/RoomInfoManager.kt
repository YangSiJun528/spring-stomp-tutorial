package dev.yangsijun.stomptutorial.room.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.domain.UserInfo
import dev.yangsijun.stomptutorial.room.dto.common.RoomInfoDto
import dev.yangsijun.stomptutorial.room.message.res.RoomInfoMessage
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*


// 이름 바꾸기 방 목록 만들어주는 객체임
class RoomInfoManager {

    companion object {
        fun execute(room: Room, newChat: BaseChat, chats: List<BaseChat>): List<RoomInfoDto> {

            val rs: Map<UUID, RoomInfoMessage> = room.userInfos.associateBy(
                { it.userId },
                { toRoomInfoMessage(chats, it, room, newChat) }
            )

            return rs.values.map { RoomInfoDto(room.id, it) }
        }

        fun execute(userIds: List<UUID>, room: Room, newChat: BaseChat, chats: List<BaseChat>): List<RoomInfoDto> {

            val rs: Map<UUID, RoomInfoMessage> = room.userInfos
                .filter { it.userId in userIds }
                .associateBy(
                    { it.userId },
                    { toRoomInfoMessage(chats, it, room, newChat) }
                )

            return rs.values.map { RoomInfoDto(room.id, it) }
        }

        fun execute(userId: UUID, room: Room, newChat: BaseChat, chats: List<BaseChat>): RoomInfoDto {

            val userInfo = room.userInfos.first { it.userId == userId }

            return userInfo.let {
                val roomInfoMessage = toRoomInfoMessage(chats, it, room, newChat)
                RoomInfoDto(room.id, roomInfoMessage)
            }
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