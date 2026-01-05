import java.io.FileWriter;
import java.io.IOException;

public class HTMLExporter implements Exporter {
    @Override
    public void export(Report report) {
        try (FileWriter fw = new FileWriter("report.html")) {
            fw.write("<html><body>");
            fw.write("<h1>" + report.getTitle() + "</h1>");
            fw.write("<p>" + report.getContent() + "</p>");
            fw.write("</body></html>");
            System.out.println("Da xuat file report.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
