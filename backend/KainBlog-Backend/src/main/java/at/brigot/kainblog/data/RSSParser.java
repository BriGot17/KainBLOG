package at.brigot.kainblog.data;


import at.brigot.kainblog.pojos.*;
import org.bson.io.BasicOutputBuffer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Parses values into an RSS-Xml element
 */
public class RSSParser {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", Locale.GERMAN);
    public String parseArticleForMongo(Article article){
        String db_string = "";

        return db_string;
    }

    private Item parseArticleToElement(Article article){
        Item item = new Item();
        item.setTitle(article.getTitle());
        item.setLink("http://placeholder.com");
        item.setDescription(article.getDescription());
        String category = "";
        List<String> categories = article.getCategory();
        for(int i = 0; i < categories.size();i++){
            if(i!=0)
                category += "/";
            category += categories.get(i);
        }
        item.setCategory(category);
        item.setGuid(article.getArticleId());
        item.setPicture(article.getPicture());
        return item;
    }

    public String parseToXMLFile(List<Article> articles) {
        Channel channel = new Channel();
        channel.setTitle("Kainblog-RSS");
        channel.setLink("www.placeholder.com");
        channel.setLanguage("DE-AT");
        channel.setPubDate(LocalDateTime.now().format(DTF));
        channel.setDescription("RSS-Feed des KainBlog-Forums");
        articles.stream().forEach(a -> {
                    channel.addItem(parseArticleToElement(a));
                });

        return channel.render();
    }

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        List<String> category = new ArrayList<>();
        category.add("Test");
        articles.add(new Article("1","Test", new User("gotped17", "Peter Gottlieb", "ABCDE", Set.of(new Role("ADMIN")), "hehe"), category, "test2", "test3","PIC"));
        RSSParser parser = new RSSParser();
        System.out.println(parser.parseToXMLFile(articles));

    }
}