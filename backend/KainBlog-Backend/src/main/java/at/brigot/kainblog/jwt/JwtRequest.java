package at.brigot.kainblog.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
