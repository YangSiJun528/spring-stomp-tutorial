package dev.yangsijun.stomptutorial.room.dto

import dev.yangsijun.stomptutorial.common.PageDto

data class RoomsInfo(
    val infos: List<RoomInfo>,
    val page: PageDto
)