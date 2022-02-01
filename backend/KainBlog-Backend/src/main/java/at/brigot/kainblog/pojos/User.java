package at.brigot.kainblog.pojos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @NonNull
    private String identity; //Kurzzeichen z.B. gotped17
    @NonNull
    private String name;
    @NonNull
    private String pwHash;
    @NonNull
    private String role;

    private String description;



}
