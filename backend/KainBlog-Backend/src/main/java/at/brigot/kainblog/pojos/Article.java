package at.brigot.kainblog.pojos;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Article {


    @NonNull
    private String articleId;
    @NonNull
    private String title;
    @NonNull
    private User publisher;
    private List<String> category;
    @NonNull
    private String description;//Preview vom Artikel
    @NonNull
    private String text;
    private String picture;


}
