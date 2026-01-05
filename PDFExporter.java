import java.io.FileWriter;
import java.io.IOException;

public class PDFExporter implements Exporter {
    @Override
    public void export(Report report) {
        try (FileWriter fw = new FileWriter("report.pdf")) {
            fw.write("=== PDF BAO CAO ===\n");
            fw.write("Tieu de: " + report.getTitle() + "\n");
            fw.write("Noi dung:\n" + report.getContent() + "\n");
            System.out.println("Da xuat file report.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
