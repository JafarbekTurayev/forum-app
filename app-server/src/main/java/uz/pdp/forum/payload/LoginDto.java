package uz.pdp.forum.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull(message = "Username bo'sh bo'lmasin")
    private String username;
    @NotNull(message = "Password bo'sh bo'lmasin")
    private String password;
}
