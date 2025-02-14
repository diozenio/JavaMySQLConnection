package domain;

public class OrderItem {
    private int id;
    private Product product;
    private int quantity;
    private double value;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.value = product.getPrice() * quantity;
    }

    public OrderItem(int id, Product product, int quantity, double value) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }

    public void print() {
        System.out.printf("Produto: %s | Quantidade: %d | Valor: R$ %.2f%n", 
            product.getName(), quantity, value);
    }
} 