package kg.alatoo.corp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import kg.alatoo.corp.entity.Message;
import kg.alatoo.corp.exception.MessageNotFoundException;
import kg.alatoo.corp.dto.MessageDto;
import kg.alatoo.corp.mapper.MessageMapper;
import kg.alatoo.corp.repository.MessageRepository;
import kg.alatoo.corp.service.MessageService;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDto createMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity(messageDto);
        Message savedMessage = messageRepository.save(message);
        return messageMapper.toDto(savedMessage);
    }

    @Override
    public MessageDto getMessageById(long id) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException("Message with '"+id+"' not found"));
        return messageMapper.toDto(message);
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream().map(messageMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteMessage(long id) {
        if (!messageRepository.existsById(id)) {
            throw new MessageNotFoundException("Message with '"+id+"' not found");
        }
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageDto> getConversation(long userId1, long userId2) {
    List<Message> messages = messageRepository
        .findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverId(
            userId1, userId2, userId2, userId1
        );
    return messages.stream()
        .map(messageMapper::toDto)
        .collect(Collectors.toList());
    }
}