package dev.yangsijun.stomptutorial.room.repository

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.room.domain.Room
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class CustomRoomRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : CustomRoomRepository {

    override fun findRoomsClosestToChatId(
        userId: UUID,
        chatId: ObjectId,
        direction: Sort.Direction,
        limit: Int
    ): List<Room> {
        val query = Query()

        val userInfosCriteria = Criteria.where("userInfos.userId").`is`(userId)
        query.addCriteria(userInfosCriteria)

        val lastViewedChatMessageCriteria = when (direction) {
            Sort.Direction.ASC -> Criteria.where("userInfos.lastViewedChatMessage").gte(chatId)
            Sort.Direction.DESC -> Criteria.where("userInfos.lastViewedChatMessage").lte(chatId)
            else -> throw IllegalArgumentException("direction must be ASC or DESC, input data : $direction")
        }
        query.addCriteria(lastViewedChatMessageCriteria)

        query.with(Sort.by(direction, "userInfos.lastViewedChatMessage"))
        query.limit(limit)

        return mongoTemplate.find(query, Room::class.java)
    }

    override fun findRoomsRecent(userId: UUID, limit: Int): List<Room> {
        val query = Query()

        val userInfosCriteria = Criteria.where("userInfos.userId").`is`(userId)
        query.addCriteria(userInfosCriteria)

        query.with(Sort.by(Sort.Direction.DESC, "id"))
        query.limit(limit)

        return mongoTemplate.find(query, Room::class.java)
    }
}
