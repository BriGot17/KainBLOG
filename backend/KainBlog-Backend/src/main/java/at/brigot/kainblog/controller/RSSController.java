package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.RSSParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins= {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT})
@RequestMapping(value = "/rss", produces = "application/xml")
public class RSSController {

    private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/feed")
    public String getRSS(){

        return rss.parseToXMLFile(articleRepository.findAllByDescriptionIsNotNull("", PageRequest.of(0, 25)));
    }

    @GetMapping
    public String home(){
        System.out.println("here");
        return "ok";
    }

}
