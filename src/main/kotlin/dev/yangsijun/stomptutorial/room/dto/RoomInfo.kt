package dev.yangsijun.stomptutorial.room.dto

import java.util.*

data class RoomInfo(
    val id: UUID,
    val name: String,
    val roomUsers: List<RoomUsersInfo>
)