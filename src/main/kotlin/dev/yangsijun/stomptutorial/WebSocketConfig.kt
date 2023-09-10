package dev.yangsijun.stomptutorial

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/sub", "/out") // 구독 or 브로커가 전달하는 주소의 prefix 정도로 이해
        config.setApplicationDestinationPrefixes("/pub", "/in") // 대충 요청 보낼때 사용하는 prefix
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/gs-guide-websocket") // stomp 연결 시 사용할 앤드포인트
            .setAllowedOriginPatterns("*")
    }
}