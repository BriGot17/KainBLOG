package at.brigot.kainblog.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Channel implements Serializable {
    protected ArrayList items = new ArrayList();
    protected ArrayList skipDays = new ArrayList();
    protected ArrayList skipHours = new ArrayList();
    protected String copyright = null;
    protected String description = null;
    protected String docs = null;
    protected String language = null;
    protected String lastBuildDate = null;
    protected String link = null;
    protected String managingEditor = null;
    protected String pubDate = null;
    protected String rating = null;
    protected String title = null;
    protected String version = "2.0";
    protected String webMaster = null;

    public void addItem(Item item) {
        synchronized(this.items) {
            this.items.add(item);
        }
    }

    public void addSkipDay(String skipDay) {
        synchronized(this.skipDays) {
            this.skipDays.add(skipDay);
        }
    }

    public void addSkipHour(String skipHour) {
        synchronized(this.skipHours) {
            this.skipHours.add(skipHour);
        }
    }

    public Item[] findItems() {
        synchronized(this.items) {
            Item[] items = new Item[this.items.size()];
            return (Item[])this.items.toArray(items);
        }
    }

    public Item[] getItems() {
        return this.findItems();
    }

    public String[] findSkipDays() {
        synchronized(this.skipDays) {
            String[] skipDays = new String[this.skipDays.size()];
            return (String[])this.skipDays.toArray(skipDays);
        }
    }

    public String[] getSkipHours() {
        return this.findSkipHours();
    }

    public String[] findSkipHours() {
        synchronized(this.skipHours) {
            String[] skipHours = new String[this.skipHours.size()];
            return (String[])this.skipHours.toArray(skipHours);
        }
    }

    public String[] getSkipDays() {
        return this.findSkipDays();
    }

    public void removeItem(Item item) {
        synchronized(this.items) {
            this.items.remove(item);
        }
    }

    public void removeSkipDay(String skipDay) {
        synchronized(this.skipDays) {
            this.skipDays.remove(skipDay);
        }
    }

    public void removeSkipHour(String skipHour) {
        synchronized(this.skipHours) {
            this.skipHours.remove(skipHour);
        }
    }

    public void render(OutputStream stream) {
        try {
            this.render((OutputStream)stream, (String)null);
        } catch (UnsupportedEncodingException var3) {
        }

    }

    public void render(OutputStream stream, String encoding) throws UnsupportedEncodingException {
        PrintWriter pw = null;
        if (encoding == null) {
            pw = new PrintWriter(stream);
        } else {
            pw = new PrintWriter(new OutputStreamWriter(stream, encoding));
        }

        this.render(pw, encoding);
        pw.flush();
    }

    public void render(Writer writer) {
        this.render((Writer)writer, (String)null);
    }

    public void render(Writer writer, String encoding) {
        PrintWriter pw = new PrintWriter(writer);
        this.render(pw, encoding);
        pw.flush();
    }

    public void render(PrintWriter writer) {
        this.render((PrintWriter)writer, (String)null);
    }

    public void render(PrintWriter writer, String encoding) {
        //writer.println("<rss version=\"0.91\">");
        writer.println(String.format("<rss version=\"%s\">",version));
        writer.println();
        writer.println("  <channel>");
        writer.println();
        writer.print("    <title>");
        writer.print(this.title);
        writer.println("</title>");
        writer.print("    <description>");
        writer.print(this.description);
        writer.println("</description>");
        writer.print("    <link>");
        writer.print(this.link);
        writer.println("</link>");
        writer.print("    <language>");
        writer.print(this.language);
        writer.println("</language>");
        if (this.rating != null) {
            writer.print("    <rating>");
            writer.print(this.rating);
            writer.println("</rating>");
        }

        if (this.copyright != null) {
            writer.print("    <copyright>");
            writer.print(this.copyright);
            writer.print("</copyright>");
        }

        if (this.pubDate != null) {
            writer.print("    <pubDate>");
            writer.print(this.pubDate);
            writer.println("</pubDate>");
        }

        if (this.lastBuildDate != null) {
            writer.print("    <lastBuildDate>");
            writer.print(this.lastBuildDate);
            writer.println("</lastBuildDate>");
        }

        if (this.docs != null) {
            writer.print("    <docs>");
            writer.print(this.docs);
            writer.println("</docs>");
        }

        if (this.managingEditor != null) {
            writer.print("    <managingEditor>");
            writer.print(this.managingEditor);
            writer.println("</managingEditor>");
        }

        if (this.webMaster != null) {
            writer.print("    <webMaster>");
            writer.print(this.webMaster);
            writer.println("</webMaster>");
        }

        writer.println();

        String[] skipDays = this.findSkipDays();
        if (skipDays.length > 0) {
            writer.println("    <skipDays>");

            for(int i = 0; i < skipDays.length; ++i) {
                writer.print("      <skipDay>");
                writer.print(skipDays[i]);
                writer.println("</skipDay>");
            }

            writer.println("    </skipDays>");
        }

        String[] skipHours = this.findSkipHours();
        if (skipHours.length > 0) {
            writer.println("    <skipHours>");

            for(int i = 0; i < skipHours.length; ++i) {
                writer.print("      <skipHour>");
                writer.print(skipHours[i]);
                writer.println("</skipHour>");
            }

            writer.println("    </skipHours>");
            writer.println();
        }

        Item[] items = this.findItems();

        for(int i = 0; i < items.length; ++i) {
            items[i].render(writer);
            writer.println();
        }

        writer.println("  </channel>");
        writer.println();
        writer.println("</rss>");
    }
}