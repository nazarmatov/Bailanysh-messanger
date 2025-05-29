package kg.alatoo.corp.mapper;

import org.springframework.stereotype.Component;
import kg.alatoo.corp.dto.MessageDto;
import kg.alatoo.corp.entity.Message;

@Component
public class MessageMapper {
    public MessageDto toDto(Message message) {
        if (message == null) {
            return null;
        }
        return new MessageDto(
                message.getId(),
                message.getSenderId(),
                message.getReceiverId(),
                message.getMessageText(),
                message.getMessageDate(),
                message.isRead()
        );
    }

    public Message toEntity(MessageDto dto) {
        if (dto == null) {
            return null;
        }
        return new Message(
                dto.getId(),
                dto.getSenderId(),
                dto.getReceiverId(),
                dto.getMessageText(),
                null,
                dto.isRead()
        );
    }
}
