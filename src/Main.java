import domain.Product;
import domain.Client;
import factories.ProductFactory;
import factories.ClientFactory;
import utils.Input;
import services.ProductService;
import services.ClientService;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        ClientService clientService = new ClientService();
        
        while (true) {
            try {
                System.out.println("\n---------- Menu ----------");
                System.out.println("1 - Cadastrar produto");
                System.out.println("2 - Cadastrar cliente");
                System.out.println("3 - Buscar produto");
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
                } else if (opcao == 2) {
                    try {
                        Client novoCliente = ClientFactory.createClient();
                        clientService.saveClient(novoCliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
                    }
                } else if (opcao == 3) {
                    try {
                        int id = Input.getInt("Digite o ID do produto: ");
                        Product produto = productService.findProductById(id);
                        if (produto != null) {
                            System.out.println("Produto encontrado:");
                            produto.print();
                        } else {
                            System.out.println("Produto não encontrado!");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao buscar produto: " + e.getMessage());
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