package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.UserRepository;
import at.brigot.kainblog.pojos.Article;
import at.brigot.kainblog.pojos.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = "/article")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {

    @Autowired
    private UserRepository userRepo;

    //private RSSParser rss = new RSSParser();
    @Autowired
    private ArticleRepository articleRepo;

    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER')")
    @PatchMapping(path = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Article> editArticle(@RequestBody Article article){
        articleRepo.deleteArticleByArticleId(article.getArticleId());

        System.out.println("be bap bap badab boop");
        articleRepo.save(article);
        return ResponseEntity.of(Optional.of(article));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER', 'USER')")
    @GetMapping("/get/{username}")
    public ResponseEntity<List<Article>> articlesByUser(@PathVariable("username") String username){
        User user = userRepo.findByUsername(username);
        return ResponseEntity.of(Optional.of(articleRepo.findByPublisher(user)));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER')")
    @PostMapping(path = "/new")
    public ResponseEntity<Article> createNewArticle(@RequestBody Article article){
        article.setArticleId(UUID.randomUUID().toString());
        articleRepo.save(article);
        return ResponseEntity.of(Optional.of(article));
    }

    @GetMapping(path = "/{guid}")
    public ResponseEntity<Article> getArticle(@PathVariable("guid") String guid){
        System.out.println("I bin ein Debug");
        return ResponseEntity.of(Optional.of(articleRepo.findArticleByArticleId(guid)));
    }

}
