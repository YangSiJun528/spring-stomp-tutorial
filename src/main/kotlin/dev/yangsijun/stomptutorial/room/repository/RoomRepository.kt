package dev.yangsijun.stomptutorial.room.repository

import dev.yangsijun.stomptutorial.chat.repository.CustomChatRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoomRepository : CrudRepository<Room, UUID>, CustomChatRepository {
}