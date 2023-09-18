package dev.yangsijun.stomptutorial.room.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.domain.SystemChat
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.domain.UserInfo
import dev.yangsijun.stomptutorial.room.dto.req.CreateRoomReq
import dev.yangsijun.stomptutorial.room.repository.RoomRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

// 방 만듦
// SYSTEM 문자가 포함되어야 함  - OOO님이 OOO,OOO 과의 대화를 시작했어요.? 암튼 뭐 이런거
// 굳이 여기 서비스일 필요는 없고, 타 서비스 객체를 통해서 해도 ㄱㅊ.
// 단, 방이 저장되는 시점에 필수로 아무 chat이나 하나는 있어야 함 - 사실 그래서 SYSTEM 메시지를 사용하는 거기도 함
@Service
class CreateRoomService(
    private val chatRepository: ChatRepository,
    private val roomRepository: RoomRepository
) {

    @Transactional(rollbackFor = [Exception::class])
    fun execute(dto: CreateRoomReq) {
        val newRoomId: UUID = UUID.randomUUID()
        val systemChatId: ObjectId = ObjectId.get()

        val systemChat = SystemChat(id = systemChatId, roomId = newRoomId, "AAA님과의 채팅이 시작되었습니다.")

        val newRoom: Room = Room.create(
            id = newRoomId, name = "AAA님과의 채팅", userInfos = toUserInfos(dto, systemChat.id)
        )

        roomRepository.save(newRoom)
        chatRepository.save(systemChat)

        // TODO Send Refresh Event
    }

    fun toUserInfos(dto: CreateRoomReq, systemChatId: ObjectId): List<UserInfo> {
        val userIds = mutableSetOf(dto.creatorId)
        userIds.addAll(dto.memberIds)
        return userIds.map { UserInfo(userId = it, lastViewedChatId = systemChatId) }
    }
}