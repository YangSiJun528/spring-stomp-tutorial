package dev.yangsijun.stomptutorial.room.repository

import dev.yangsijun.stomptutorial.message.repository.CustomMessageRepository
import dev.yangsijun.stomptutorial.room.domain.Room
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoomRepository : CrudRepository<Room, UUID>, CustomMessageRepository {
}