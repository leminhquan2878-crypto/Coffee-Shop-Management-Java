import java.io.FileWriter;
import java.io.IOException;

public class ExcelExporter implements Exporter {
    @Override
    public void export(Report report) {
        try (FileWriter fw = new FileWriter("report.xlsx")) {
            fw.write("Tieu de\tNoi dung\n");
            fw.write(report.getTitle() + "\t" + report.getContent() + "\n");
            System.out.println("Da xuat file report.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
