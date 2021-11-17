package at.brigot.kainblog.data;

import org.apache.commons.digester.rss.*;
import at.brigot.kainblog.pojos.Article;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Parses values into an RSS-Xml element
 */
public class RSSParser {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss", Locale.GERMAN);

    private Item parseArticleToElement(Article article){
        Item item = new Item();
        item.setTitle(article.getTitle());
        item.setLink("http://placeholder.com");
        item.setDescription(article.getDescription());
        return item;
    }

    public String parseToXMLFile(List<Article> articles){
        String raw = "";
        Channel channel = new Channel();
        channel.setTitle("Kainblog-RSS");
        channel.setPubDate(LocalDateTime.now().format(DTF));
        channel.setDescription("RSS-Feed des KainBlog-Forums");
        articles.stream().forEach(a -> {
                    channel.addItem(parseArticleToElement(a));
                });
        try{
            OutputStream out = new FileOutputStream("feed.xml");
            channel.render(out);
            out.close();
            BufferedReader in = new BufferedReader(new FileReader("feed.xml"));
            raw = in.lines().collect(Collectors.joining());
            in.close();
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raw;
    }

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("Test", "test2", "test3"));
        RSSParser parser = new RSSParser();
        parser.parseToXMLFile(articles);
    }
}