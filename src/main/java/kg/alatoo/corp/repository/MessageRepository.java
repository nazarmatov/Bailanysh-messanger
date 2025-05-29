package kg.alatoo.corp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kg.alatoo.corp.entity.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {  
    Optional<Message> findBySenderId(long senderId);
    Optional<Message> findByReceiverId(long receiverId);
    List<Message> findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverId(
    long senderId1, long receiverId1, long senderId2, long receiverId2
);
}
