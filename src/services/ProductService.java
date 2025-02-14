package services;

import domain.Product;
import dao.ProductDAO;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public Product findProductById(int id) {
        return productDAO.findById(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
