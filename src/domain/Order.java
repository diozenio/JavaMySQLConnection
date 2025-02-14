package domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Client client;
    private Employee employee;
    private List<OrderItem> items;
    private double totalValue;

    public Order(Client client, Employee employee) {
        this.client = client;
        this.employee = employee;
        this.items = new ArrayList<>();
        this.totalValue = 0.0;
    }

    public Order(int id, Client client, Employee employee, double totalValue) {
        this.id = id;
        this.client = client;
        this.employee = employee;
        this.items = new ArrayList<>();
        this.totalValue = totalValue;
    }

    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
        totalValue += item.getValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void print() {
        System.out.printf("ID: %d | Cliente: %s | Funcionário: %s | Valor Total: R$ %.2f%n", 
            id, client.getName(), employee.getName(), totalValue);
        if (!items.isEmpty()) {
            System.out.println("---------- Itens do Pedido ----------");
            for (OrderItem item : items) {
                item.print();
            }
            System.out.println("------------------------------------");
        }
    }
} 