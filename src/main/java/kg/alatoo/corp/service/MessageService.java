package kg.alatoo.corp.service;

import java.util.List;
import kg.alatoo.corp.dto.MessageDto;

public interface MessageService {
    public MessageDto createMessage(MessageDto messageDto);
    public MessageDto getMessageById(long id);
    public void deleteMessage(long id);
    public List<MessageDto> getAllMessages();
    public List<MessageDto> getConversation(long userId1, long userId2);
}
