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

    public String render() {
        //writer.println("<rss version=\"0.91\">");
        String rss = "";
        rss += String.format("<rss version=\"%s\">",version);
        rss+="\n\n    <channel>";
        rss+="\n        <title>" + title + "</title>";
        rss+="\n        <description>" + this.description + "</description>";
        rss+="\n    <link>" + this.link + "</link>";
        rss+="\n    <language>" +this.language + "</language>";

        if(this.rating != null){
            rss+= "\n    <rating>" + this.rating +"</rating>";
        }
        if(this.copyright != null){
            rss+= "\n    <copyright>" + this.copyright + "</copyright>";
        }

        if(this.pubDate != null){
            rss+= "\n    <pubDate>" + this.pubDate + "</pubDate>\n";
        }

        if(this.lastBuildDate != null){
            rss += "\n    <lastBuildDate>" + this.lastBuildDate + "</lastBuildDate>";
        }

        if(this.docs != null){
            rss += "\n    <docs>" + this.docs + "</docs>";
        }

        if(this.managingEditor != null){
            rss += "\n    <managingEditor>" + this.managingEditor + "</managingEditor>";
        }

        if(this.webMaster != null){
            rss += "\n    <webMaster>" + this.webMaster + "</webMaster>\n";
        }


        String[] skipDays = this.findSkipDays();
        if (skipDays.length > 0) {
            rss+= "\n        <skipDays>";

            for(int i = 0; i < skipDays.length; ++i) {
                rss += "            <skipDay>" + skipDays[i] + "</skipDay>";
            }
            rss +="        </skipDays>";
        }

        String[] skipHours = this.findSkipHours();
        if (skipHours.length > 0) {
            rss+= "\n    <skipHours>";

            for(int i = 0; i < skipHours.length; ++i) {
                rss += "            <skipHour>" + skipHours[i] + "</skipHour>";
            }
            rss +="        </skipHours>";
        }

        Item[] items = this.findItems();

        for(int i = 0; i < items.length; ++i) {
            rss += items[i].render() + "\n";
        }
        rss += " \n   </channel>\n\n</rss>";
        return rss;
    }
}