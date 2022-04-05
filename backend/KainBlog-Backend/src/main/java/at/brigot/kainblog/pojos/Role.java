package at.brigot.kainblog.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {

    private String name;
    public String getName(){
        return this.name;
    }
}
