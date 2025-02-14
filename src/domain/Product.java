package domain;

public class Product {
    protected int ID;
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(int ID, String name, double price, int quantity) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "ID=" + ID +
                ", nome='" + name + '\'' +
                ", preco=" + price +
                ", quantidade=" + quantity +
                '}';
    }

    public void print() {
        System.out.println("---------- Dados do Produto ----------");
        System.out.println("ID: " + ID);
        System.out.println("Nome: " + name);
        System.out.printf("Preço: R$ %.2f%n", price);
        System.out.println("Quantidade: " + quantity);
        System.out.println("------------------------------------");
    }
}
