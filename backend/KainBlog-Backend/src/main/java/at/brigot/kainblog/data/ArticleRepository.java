package at.brigot.kainblog.data;

import at.brigot.kainblog.pojos.Article;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    public Article findArticleByTitle (String title);
    //public Article findArticleByArticleId (String id);
    public Optional<Article> findArticleByArticleId(String id);
    public List<Article> findAllByDescriptionIsNotNull(String description, Pageable pageable);
    public List<Article> findAllByDescriptionIsNotNull(String description);
    public void deleteArticleByArticleId(String id);


}
