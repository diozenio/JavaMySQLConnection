package controllers;

import domain.Product;
import factories.ProductFactory;
import services.ProductService;
import utils.Input;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import exceptions.DatabaseException;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    public void registerProduct() {
        try {
            Product novoProduto = ProductFactory.createProduct();
            productService.saveProduct(novoProduto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (ValidationException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void findProduct() {
        try {
            int id = Input.getInt("Digite o ID do produto: ");
            Product produto = productService.findProductById(id);
            produto.print();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public void listProducts() {
        try {
            List<Product> produtos = productService.getAllProducts();
            if (produtos.isEmpty()) {
                System.out.println("Não há produtos cadastrados!");
            } else {
                System.out.println("\n---------- Lista de Produtos ----------");
                for (Product produto : produtos) {
                    produto.print();
                }
                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }
} 