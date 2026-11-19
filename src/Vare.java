public class Vare {

    private String id;
    private String name;
    private String priceDate;
    private double price;
    private boolean isBudgetBrand;
    private double quantity;
    private String unit;
    private boolean isWeighted;
    private boolean isBio;
    private boolean isAvailable;
    private double discountPrice;
    private boolean isDiscounted;
    private LoadManager lm;

    public Vare(String id, String name, String priceDate, double price, boolean isBudgetBrand, double quantity, String unit, boolean isWeighted, boolean isBio, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.priceDate = priceDate;
        this.price = price;
        this.isBudgetBrand = isBudgetBrand;
        this.quantity = quantity;
        this.unit = unit;
        this.isWeighted = isWeighted;
        this.isBio = isBio;
        this.isAvailable = isAvailable;
        this.discountPrice = price;
        this.isAvailable = false;
    }

    @Override
    public String toString() {
        return name + " " + price + " kr.";
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setDiscounted(boolean discounted) {
        isDiscounted = discounted;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

}
