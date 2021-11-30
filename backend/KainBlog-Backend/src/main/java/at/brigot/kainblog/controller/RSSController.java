package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.DB_Access;
import at.brigot.kainblog.data.RSSParser;
import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/rss", produces = "application/xml")
@RestController
@CrossOrigin(origins= {"http://localhost:3000", "http://localhost:14000"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT})
public class RSSController {

    private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/feed")
    public String getRSS(){
        System.out.println("here2");
        articleRepository.deleteAll();
        articleRepository.save(new Article("1","Test","desc", "hello"));
        articleRepository.save(new Article("2","Test2","desc","texting"));
        articleRepository.save(new Article("3","Peter","Boss","Yes"));
        return rss.parseToXMLFile(articleRepository.findAllByDescriptionIsNotNull("", PageRequest.of(0, 25)));
    }

    @GetMapping
    public String home(){
        System.out.println("here");
        return "ok";
    }

}
