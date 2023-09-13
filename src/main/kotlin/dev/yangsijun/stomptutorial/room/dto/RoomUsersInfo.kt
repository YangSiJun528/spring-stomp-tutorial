package dev.yangsijun.stomptutorial.room.dto

import java.util.UUID

data class RoomUsersInfo(
    val id: UUID,
    val name: String,
    val profileImageUrl: String
)
