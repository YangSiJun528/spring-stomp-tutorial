package dev.yangsijun.stomptutorial

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.HtmlUtils


@RestController
class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @Throws(Exception::class)
    fun greeting(message: HelloMessage): Greeting {
        Thread.sleep(1000) // simulated delay
        return Greeting("Hello, " + HtmlUtils.htmlEscape(message.name) + "!")
    }
}