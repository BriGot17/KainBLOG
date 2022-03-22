package at.brigot.kainblog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String username;
    private String password;
    private Set<String> roles;
    // getters and setters
}
