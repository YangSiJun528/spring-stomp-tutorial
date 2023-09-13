package dev.yangsijun.stomptutorial.room.sender

import dev.yangsijun.stomptutorial.room.dto.RoomChat
import dev.yangsijun.stomptutorial.room.dto.RoomInfoPages
import org.springframework.messaging.simp.SimpMessagingTemplate

class RoomSender(
    private val template: SimpMessagingTemplate
) {
    // TODO 방 정보 보내기 - list로 보냄
    fun send1(path: String, info: RoomInfoPages) {
        template.convertAndSend(path, info)
    }

    // TODO 채팅 목록에서 채팅 갱신
    fun send2(path: String, info: List<RoomChat>) {
        template.convertAndSend(path, info)
    }
}