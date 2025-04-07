package kg.alatoo.corp.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "ID cannot be empty")
    private long id;
    @NotBlank(message = "username cannot  be empty")
    private String userName;
    @NotBlank(message = "password cannot be empty")
    private String userPassword;

}
