package kg.alatoo.corp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

import kg.alatoo.corp.dto.MessageDto;
import kg.alatoo.corp.service.MessageService;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@Valid @RequestBody MessageDto messageDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.createMessage(messageDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

}
