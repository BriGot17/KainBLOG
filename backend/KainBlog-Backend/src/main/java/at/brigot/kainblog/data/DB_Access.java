package at.brigot.kainblog.data;

import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Creates a Connection to the Mongo DB
 */

public class DB_Access implements CommandLineRunner {
    private static RSSParser rss = new RSSParser();

    @Autowired
    private ArticleRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(DB_Access.class,args);
    }

    public List<Article> getItemsForRSSFeed(){
        return repository.findAllByDescriptionIsNotNull("", PageRequest.of(0,25));
    }
    public void getItemsForFullFeed(){
        rss.parseToXMLFile(repository.findAllByDescriptionIsNotNull("" ));
    }

    @Override
    public void run(String...args) throws Exception{

        repository.deleteAll();
        repository.save(new Article("Test","desc", "hello"));
        repository.save(new Article("Test2","desc","texting"));
        repository.save(new Article("Peter","Boss","Yes"));

        System.out.println(repository.findArticleByTitle("Test"));
        System.out.println(repository.findArticleByTitle("Test2"));

    }

}
