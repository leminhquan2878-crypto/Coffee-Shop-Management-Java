public class ExporterFactory {
    // Factory Method: tạo Exporter theo định dạng
    public static Exporter create(String type) {
        if (type == null) return null;
        switch (type.toLowerCase()) {
            case "pdf": return new PDFExporter();
            case "excel": return new ExcelExporter();
            case "html": return new HTMLExporter();
            default: return null;
        }
    }
}
