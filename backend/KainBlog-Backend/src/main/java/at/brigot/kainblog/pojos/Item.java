package at.brigot.kainblog.pojos;

import java.io.PrintWriter;
import java.io.Serializable;

public class Item implements Serializable {
    protected String description = null;
    protected String category = null;
    protected String link = null;
    protected String title = null;
    protected String guid = null;
    protected String picture = null;

    public Item() {
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuid() {
        return (this.guid);
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPicture() {
        return (this.picture);
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    void render(PrintWriter writer) {

        writer.println("<item>");

        writer.print("\t<title>");
        writer.print(title);
        writer.println("</title>");

        writer.print("      <link>");
        writer.print(link);
        writer.println("</link>");

        if (description != null) {
            writer.print("      <description>");
            writer.print(description);
            writer.println("</description>");
        }

        writer.print("      <category>");
        writer.print(category);
        writer.println("</category>");

        writer.print("      <guid>");
        writer.print(guid);
        writer.println("</guid>");

        writer.println(String.format("      <enclosure url=\"%s\"/>", picture));

        writer.println("    </item>");

    }
    String render(){
        String rss = "\t\t<item>";
        rss += "\t\t\t<title>" + title + "</title>\n";
        rss+= "\t\t\t<link>" + link + "</link>\n";
        rss+= "\t\t\t<description>" + description + "</link>\n";
        rss += "\t\t\t<category>" + category + "</category>\n";
        rss += "\t\t\t<guid>" + guid + "</guid>\n";
        rss += "\t\t\t<enclosure url=" + picture + "/>\n";
        rss += "\t\t</item>";
        return rss;
    }

}
