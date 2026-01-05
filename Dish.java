public class Dish {
    private String id;
    private String name;
    private String size;
    private String category;
    private double price;

    // Builder Pattern
    public static class DishBuilder {
        private String id;
        private String name;
        private String size;
        private String category;
        private double price;

        public DishBuilder id(String id) { this.id = id; return this; }
        public DishBuilder name(String name) { this.name = name; return this; }
        public DishBuilder size(String size) { this.size = size; return this; }
        public DishBuilder category(String category) { this.category = category; return this; }
        public DishBuilder price(double price) { this.price = price; return this; }
        public Dish build() { return new Dish(this); }
    }

    private Dish(DishBuilder b) {
        this.id = b.id;
        this.name = b.name;
        this.size = b.size;
        this.category = b.category;
        this.price = b.price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSize() { return size; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
