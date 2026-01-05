public class Promotion {
    private String code;
    private double percent; // 0..100

    public Promotion(String code, double percent) {
        this.code = code;
        this.percent = percent;
    }

    public String getCode() { return code; }
    public double getPercent() { return percent; }

    @Override
    public String toString() { return code + " (" + percent + "%)"; }
}
