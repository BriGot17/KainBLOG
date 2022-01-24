package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.DB_Access;
import at.brigot.kainblog.data.RSSParser;
import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT})
@RequestMapping(value = "/rss", produces = "application/xml")
public class RSSController {

    private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/feed")
    public String getRSS(){
        System.out.println("here2");
        List<String> categories = new ArrayList<>();
        categories.add("Computers");
        articleRepository.deleteAll();
        articleRepository.save(new Article("1","Test",categories,"desc", "hello","PIC"));
        categories.add("Automatisierung");
        articleRepository.save(new Article("2","Test2",categories,"desc","texting","PIC"));
        categories.clear();
        categories.add("Informatik");
        articleRepository.save(new Article("3","Peter",categories,"Boss","Yes","PIC"));
        return rss.parseToXMLFile(articleRepository.findAllByDescriptionIsNotNull("", PageRequest.of(0, 25)));
    }

    @GetMapping
    public String home(){
        System.out.println("here");
        return "ok";
    }

}
