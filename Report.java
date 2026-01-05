
public class Report {

    private String reportId;
    private String title;
    private String content;

    public Report(String reportId, String title, String content) {
        this.reportId = reportId;
        this.title = title;
        this.content = content;
    }

    public String getReportId() {
        return reportId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    public interface Exporter {
    void export(Report report);
}

}

