package factories;

import domain.Product;
import utils.Input;

public class ProductFactory {
    public static Product createProduct() {
        String name = Input.getString("Digite o nome do produto: ");
        double price = Input.getDouble("Digite o preço do produto: ");
        int quantity = Input.getInt("Digite a quantidade do produto: ");
        return new Product(name, price, quantity);
    }
}