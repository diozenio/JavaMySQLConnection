import domain.Product;
import factories.ProductFactory;
import utils.Input;
import services.ProductService;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        
        while (true) {
            try {
                System.out.println("\n---------- Menu ----------");
                System.out.println("1 - Cadastrar produto");
                System.out.println("0 - Sair");
                
                int opcao = Input.getInt("Digite sua opção: ");
                
                if (opcao == 0) {
                    System.out.println("Programa finalizado!");
                    break;
                } else if (opcao == 1) {
                    try {
                        Product novoProduto = ProductFactory.createProduct();
                        productService.saveProduct(novoProduto);
                        System.out.println("Produto cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar produto: " + e.getMessage());
                    }
                } else {
                    System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }
}