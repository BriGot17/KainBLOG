package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/article")
@RestController
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT})
public class ArticleController {

    //private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepo;

    @PatchMapping(path = "/edit/{guid}")
    public ResponseEntity<Article> editArticle(@RequestBody Article article){

        articleRepo.deleteArticleByArticleId(article.getArticleId());
        articleRepo.save(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Article> createNewArticle(@RequestBody Article article){
        System.out.println(article.toString());
        article.setArticleId(UUID.randomUUID().toString());
        articleRepo.save(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping(path = "/{guid}")
    public ResponseEntity<Article> getArticle(@PathVariable("guid") String guid){
        System.out.println("I bims eins Debug");
        return ResponseEntity.of(articleRepo.findArticleByArticleId(guid));
    }

}
