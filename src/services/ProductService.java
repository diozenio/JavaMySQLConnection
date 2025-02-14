package services;

import domain.Product;
import dao.ProductDAO;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void saveProduct(Product product) {
        productDAO.save(product);
    }
}
