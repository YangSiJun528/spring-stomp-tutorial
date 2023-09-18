package dev.yangsijun.stomptutorial.chat.repository

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
class CustomChatRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : CustomChatRepository {

    override fun findChatsClosestToId(chatId: ObjectId, roomId: UUID, direction: Int, limit: Int): List<BaseChat> {
        val query = Query().with(Pageable.ofSize(limit)).with(Sort.by(Sort.Direction.ASC))

        // roomId가 같은 경우만 추가
        val criteria = Criteria.where("id")
            .let {
                it.andOperator(
                    Criteria.where("roomId").`is`(roomId),
                    when (direction) {
                        1 -> it.gte(chatId)
                        -1 -> it.lte(chatId)
                        else -> throw IllegalArgumentException("direction is 1 or -1, input data : $direction")
                    }
                )
            }

        query.addCriteria(criteria)
        return mongoTemplate.find(query, BaseChat::class.java)
    }

    override fun findChatsRecent(roomId: UUID, limit: Int): List<BaseChat> {
        val query = Query().with(Pageable.ofSize(limit)).with(Sort.by(Sort.Direction.ASC))

        // roomId가 같은 경우만 추가
        val criteria = Criteria.where("roomId").`is`(roomId)

        query.addCriteria(criteria)
        return mongoTemplate.find(query, BaseChat::class.java)
    }
}