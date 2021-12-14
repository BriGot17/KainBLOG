package at.brigot.kainblog.data;

import at.brigot.kainblog.pojos.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
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

        List<String> categories = new ArrayList<>();
        categories.add("Computers");

        repository.deleteAll();
        repository.save(new Article("1","Test",categories,"desc", "hello","PIC"));
        categories.add("Test");
        repository.save(new Article("2","Test2",categories,"desc","texting","PIC"));
        categories.add("Informatik");
        repository.save(new Article("3","Peter",categories,"Boss","Yes","PIC"));

        System.out.println(repository.findArticleByTitle("Test"));
        System.out.println(repository.findArticleByTitle("Test2"));

    }

}
