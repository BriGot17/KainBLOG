package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.RSSParser;
import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String createArticle(@RequestBody String body){
        System.out.println("hello");
        System.out.println(body);
        return body;
    }

    @GetMapping(path = "/id")
    public String readArticle(){

        return null;
    }

}
