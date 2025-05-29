package kg.alatoo.corp.controller;

import kg.alatoo.corp.entity.Message;
import kg.alatoo.corp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepository messageRepository;

    @MessageMapping("/chat")
    public void processMessage(Message message) {
        if (message.getSenderId() == null || message.getReceiverId() == null) return;
        messageRepository.save(message);
        messagingTemplate.convertAndSend("/queue/messages/" + message.getReceiverId(), message);
    }
}