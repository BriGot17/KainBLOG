package at.brigot.kainblog.pojos;

import java.io.PrintWriter;
import java.io.Serializable;

public class Item implements Serializable {
    protected String description = null;
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

        writer.println("    <item>");

        writer.print("      <title>");
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

        writer.print("      <guid>");
        writer.print(guid);
        writer.println("</guid>");

        writer.println(String.format("      <enclosure url=\"%s\"/>", picture));

        writer.println("    </item>");

    }

}
