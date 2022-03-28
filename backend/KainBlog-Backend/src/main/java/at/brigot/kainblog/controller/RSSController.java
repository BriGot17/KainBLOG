package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.RSSParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET,RequestMethod.HEAD,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.DELETE,RequestMethod.TRACE})
@RequestMapping(value = "/rss", produces = "application/xml")
public class RSSController {

    private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(path = "/feed")
    public String getRSS(){
        System.out.println("here yay");
        return rss.parseToXMLFile(articleRepository.findAllByDescriptionIsNotNull("", PageRequest.of(0, 25)));
    }

}
