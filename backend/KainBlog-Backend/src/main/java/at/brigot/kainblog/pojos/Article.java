package at.brigot.kainblog.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String articleId;
    private String title;
    //Autor
    //Publisher
    private String description;//Preview vom Artikel
    private String text;


}
