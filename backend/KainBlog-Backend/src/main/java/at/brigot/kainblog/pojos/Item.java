package at.brigot.kainblog.pojos;

import java.io.PrintWriter;

public class Item extends org.apache.commons.digester.rss.Item {

    public Item() {
        super();
    }

    protected String guid = null;

    public String getGuid() {
        return (this.guid);
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

        writer.println("    </item>");

    }

}
