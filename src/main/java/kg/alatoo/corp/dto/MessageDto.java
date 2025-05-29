package kg.alatoo.corp.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    @NotBlank
    private Long id;
    @NotBlank
    private Long senderId;
    @NotBlank
    private Long receiverId;
    @NotBlank(message = "messageText cannot be empty")
    private String messageText;
    @NotBlank
    private LocalDateTime messageDate;
    private boolean isRead;
}
