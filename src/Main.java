import domain.Product;
import domain.Client;
import domain.Employee;
import domain.Order;
import factories.ProductFactory;
import factories.ClientFactory;
import utils.Input;
import services.ProductService;
import services.ClientService;
import services.EmployeeService;
import services.OrderService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        ClientService clientService = new ClientService();
        EmployeeService employeeService = new EmployeeService();
        OrderService orderService = new OrderService();
        
        while (true) {
            try {
                System.out.println("\n---------- Menu ----------");
                System.out.println("1 - Cadastrar produto");
                System.out.println("2 - Cadastrar cliente");
                System.out.println("3 - Buscar produto");
                System.out.println("4 - Listar produtos");
                System.out.println("5 - Efetuar venda");
                System.out.println("6 - Listar vendas");
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
                } else if (opcao == 4) {
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
                } else if (opcao == 5) {
                    try {
                        String cpfCliente = Input.getString("Digite o CPF do cliente: ");
                        Client cliente = clientService.findClientByCpf(cpfCliente);
                        if (cliente == null) {
                            System.out.println("Cliente não encontrado!");
                            continue;
                        }

                        String cpfFuncionario = Input.getString("Digite o CPF do funcionário: ");
                        Employee funcionario = employeeService.findEmployeeByCpf(cpfFuncionario);
                        if (funcionario == null) {
                            System.out.println("Funcionário não encontrado!");
                            continue;
                        }

                        Order pedido = new Order(cliente, funcionario);

                        while (true) {
                            int idProduto = Input.getInt("Digite o ID do produto (0 para finalizar): ");
                            if (idProduto == 0) break;

                            Product produto = productService.findProductById(idProduto);
                            if (produto == null) {
                                System.out.println("Produto não encontrado!");
                                continue;
                            }

                            int quantidade = Input.getInt("Digite a quantidade: ");
                            pedido.addItem(produto, quantidade);
                        }

                        if (pedido.getItems().isEmpty()) {
                            System.out.println("Pedido cancelado - nenhum item adicionado!");
                            continue;
                        }

                        orderService.saveOrder(pedido);
                        System.out.println("Venda realizada com sucesso!");
                        pedido.print();

                    } catch (Exception e) {
                        System.out.println("Erro ao efetuar venda: " + e.getMessage());
                    }
                } else if (opcao == 6) {
                    try {
                        List<Order> pedidos = orderService.getAllOrders();
                        if (pedidos.isEmpty()) {
                            System.out.println("Não há vendas registradas!");
                        } else {
                            System.out.println("\n---------- Lista de Vendas ----------");
                            for (Order pedido : pedidos) {
                                pedido.print();
                            }
                            System.out.println("------------------------------------");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao listar vendas: " + e.getMessage());
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