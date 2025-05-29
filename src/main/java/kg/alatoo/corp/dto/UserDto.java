package kg.alatoo.corp.dto;
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
public class UserDto {
    @NotBlank
    private long id;
    @NotBlank(message = "username cannot  be empty")
    private String userName;
    @NotBlank(message = "password cannot be empty")
    private String userPassword;

}
