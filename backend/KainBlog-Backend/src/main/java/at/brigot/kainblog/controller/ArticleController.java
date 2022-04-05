package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.UserRepository;
import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = "/article")
@RestController
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET,RequestMethod.HEAD,RequestMethod.OPTIONS,RequestMethod.PUT,RequestMethod.POST,RequestMethod.PATCH,RequestMethod.DELETE,RequestMethod.TRACE})
public class ArticleController {

    @Autowired
    private UserRepository userRepo;

    //private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepo;

    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER')")
    @PatchMapping(path = "/edit", consumes = "multipart/form-data")
    public ResponseEntity<Article> editArticle(@RequestBody MultiValueMap<String, String> userFormData){
        Article article = new Article();
        article.setTitle(userFormData.get("title").get(0));
        article.setCategory(userFormData.get("category"));
        article.setDescription(userFormData.get("description").get(0));

        articleRepo.deleteArticleByArticleId(article.getArticleId());
        if(article.getPicture() == null){
            article.setPicture("data:image/png;base64,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");
        }
        System.out.println("be bap bap badab boop");
        articleRepo.save(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER', 'USER')")
    @GetMapping("/get/{username}")
    public ResponseEntity<List<Article>> articlesByUser(@PathVariable("username") String username){
        User user = userRepo.findByUsername(username);
        return ResponseEntity.of(Optional.of(articleRepo.findByPublisher(user)));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER')")
    @PostMapping(path = "/new", consumes = "multipart/form-data" )
    public ResponseEntity<Article> createNewArticle(@RequestBody MultiValueMap<String, String> userFormData){
        User user = userRepo.findByUsername(userFormData.get("publisher").get(0));
        Article article = new Article(UUID.randomUUID().toString(), userFormData.get("title").get(0), user, userFormData.get("category"),
                    userFormData.get("description").get(0), userFormData.get("text").get(0), userFormData.get("picture").get(0));
        System.out.println(article.toString());

        if(article.getPicture() == null){
            article.setPicture("data:image/png;base64,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");
        }
        articleRepo.save(article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }


    @GetMapping(path = "/{guid}")
    public ResponseEntity<Article> getArticle(@PathVariable("guid") String guid){
        System.out.println("I bims eins Debug");
        return ResponseEntity.of(Optional.of(articleRepo.findArticleByArticleId(guid)));
    }

}
