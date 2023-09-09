package dev.yangsijun.stomptutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StompTutorialApplication

fun main(args: Array<String>) {
    runApplication<StompTutorialApplication>(*args)
}
