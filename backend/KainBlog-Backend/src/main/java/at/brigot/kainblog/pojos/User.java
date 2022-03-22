package at.brigot.kainblog.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private static final long serialVersionUID = 2396654715019746670L;

    private String id;
    private String username; //Kurzzeichen z.B. gotped17
    private String name;
    private String password;
    private List<String> roles;
    private String description;


}
