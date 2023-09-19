package dev.yangsijun.stomptutorial.chat.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.message.res.OutChatMessage
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import dev.yangsijun.stomptutorial.room.domain.UserInfo
import dev.yangsijun.stomptutorial.room.repository.RoomRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

// 채팅 목록 돌려줌
@Service
class ViewChatService(
    private val chatRepository: ChatRepository,
    private val roomRepository: RoomRepository
) {

    fun execute(chatId: ObjectId, userId: UUID) {
        val chat = chatRepository.findById(chatId)
            .orElseThrow { throw RuntimeException("Can't find Chat by input ID : $chatId") }
        val room = roomRepository.findById(chat.roomId)
            .orElseThrow { throw RuntimeException("Can't find Room by input ID : ${chat.roomId}") }

        val userInfo = room.userInfos.first { it.userId == userId }
        val newUserInfo = UserInfo(userInfo.userId, chatId)

        val updatedUserInfos = room.userInfos.filter { it.userId != userId } + newUserInfo

        val updatedRoom = Room.update(room.id, room.name, updatedUserInfos)

        roomRepository.save(updatedRoom)
    }

    // 테스트용 임시
    fun execute(roomId: UUID, userId: UUID) {
        val chats = chatRepository.findAllByRoomIdOrderByIdDesc(roomId)
        val room = roomRepository.findById(chats.get(0).roomId)
            .orElseThrow { throw RuntimeException("Can't find Room by input ID : ${chats.get(0).roomId}") }

        val userInfo = room.userInfos.first { it.userId == userId }
        val newUserInfo = UserInfo(userInfo.userId, chats.get(0).id)

        val updatedUserInfos = room.userInfos.filter { it.userId != userId } + newUserInfo

        val updatedRoom = Room.update(room.id, room.name, updatedUserInfos)

        roomRepository.save(updatedRoom)
    }
}
