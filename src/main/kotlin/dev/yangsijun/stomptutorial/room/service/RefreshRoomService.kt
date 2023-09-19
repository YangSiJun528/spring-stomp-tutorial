package dev.yangsijun.stomptutorial.room.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.domain.UserInfo
import dev.yangsijun.stomptutorial.room.dto.common.RoomInfoDto
import dev.yangsijun.stomptutorial.room.message.res.RoomInfoMessage
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*


// 방 정보 갱신
@Service
class RefreshRoomService(
    private val chatRepository: ChatRepository
) {

    fun execute(room: Room, newChat: BaseChat): List<RoomInfoDto> {
        val chats: List<BaseChat> = chatRepository.findAllByRoomIdOrderByIdDesc(roomId = room.id)

        val roomInfos = RoomInfoManager.execute(room, newChat, chats)

        return roomInfos
    }
}