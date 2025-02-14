import controllers.ProductController;
import controllers.ClientController;
import controllers.SaleController;
import utils.Input;

public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        ClientController clientController = new ClientController();
        SaleController saleController = new SaleController();
        
        while (true) {
            try {
                printMenu();
                int opcao = Input.getInt("Digite sua opção: ");
                
                switch (opcao) {
                    case 0:
                        System.out.println("Programa finalizado!");
                        return;
                    case 1:
                        productController.registerProduct();
                        break;
                    case 2:
                        clientController.registerClient();
                        break;
                    case 3:
                        productController.findProduct();
                        break;
                    case 4:
                        productController.listProducts();
                        break;
                    case 5:
                        saleController.makeSale();
                        break;
                    case 6:
                        saleController.listSales();
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n---------- Menu ----------");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Cadastrar cliente");
        System.out.println("3 - Buscar produto");
        System.out.println("4 - Listar produtos");
        System.out.println("5 - Efetuar venda");
        System.out.println("6 - Listar vendas");
        System.out.println("0 - Sair");
    }
}