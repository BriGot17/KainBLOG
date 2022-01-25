package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.RSSParser;
import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(value = "/article")
@RestController
@CrossOrigin(origins= {"http://localhost:3000", "http://localhost:14000"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT})
public class ArticleController {

    private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepository;

    @PutMapping(path = "/edit")
    public String editArticle(Article article){
        Article a = null;

        articleRepository.save(a);
        return "feed";
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Article> createNewArticle(@RequestBody Article article){
        System.out.println(article.toString());
        article.setArticleId(UUID.randomUUID().toString());
        articleRepository.save(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public String readArticle(){

        return null;
    }

}
