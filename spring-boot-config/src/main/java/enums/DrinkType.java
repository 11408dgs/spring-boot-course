package enums;

public enum DrinkType {
    COFFEE("咖啡",20.0), TEA("茶",10.0), WATER("水",2.0), COKE("可乐",3.5);
    private final String label;
    private final double price;

    DrinkType(String label,double  price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }
    public double getPrice() {
        return price;
    }
}
