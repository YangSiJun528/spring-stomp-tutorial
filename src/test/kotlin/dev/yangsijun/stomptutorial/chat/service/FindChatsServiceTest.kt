package dev.yangsijun.stomptutorial.chat.service

import dev.yangsijun.stomptutorial.chat.domain.BaseChat
import dev.yangsijun.stomptutorial.chat.domain.ChatType
import dev.yangsijun.stomptutorial.chat.repository.ChatRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.bson.types.ObjectId
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

//@SpringBootTest(classes = [FindChatsService::class])
//class FindChatsServiceTest : BehaviorSpec({
//
//    val chatRepository: ChatRepository = mockk<ChatRepository>()
//
//    val findChatsService: FindChatsService = FindChatsService(chatRepository)
//
//    val roomId: UUID = UUID.randomUUID()
//    val chatId1: ObjectId = ObjectId.get()
//    val chatId2: ObjectId = ObjectId.get()
//    val chatId3: ObjectId = ObjectId.get()
//
//
//    val chats: List<BaseChat> = listOf(
//        BaseChat.update(id = chatId1, roomId = roomId, userId = UUID.randomUUID(), type = ChatType.USER, textContent = "AAA"),
//        BaseChat.update(id = chatId2, roomId = roomId, userId = UUID.randomUUID(), type = ChatType.USER, textContent = "BBB"),
//        BaseChat.update(id = chatId3, roomId = roomId, userId = UUID.randomUUID(), type = ChatType.USER, textContent = "CCC")
//    )
//
//    Given("aa") {
//        every { chatRepository.findChatsClosestToId(chatId = chatId2, roomId = roomId, direction = 1, limit = 2) }
//    }
//})