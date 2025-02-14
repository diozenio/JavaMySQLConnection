package controllers;

import domain.Client;
import domain.Employee;
import domain.Order;
import domain.Product;
import services.ClientService;
import services.EmployeeService;
import services.OrderService;
import services.ProductService;
import utils.Input;
import java.util.List;
import exceptions.ConnectionException;
import exceptions.DatabaseException;

public class SaleController {
    private final OrderService orderService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final ProductService productService;

    public SaleController() {
        this.orderService = new OrderService();
        this.clientService = new ClientService();
        this.employeeService = new EmployeeService();
        this.productService = new ProductService();
    }

    public void makeSale() {
        try {
            String cpfCliente = Input.getString("Digite o CPF do cliente: ");
            Client cliente = clientService.findClientByCpf(cpfCliente);
            if (cliente == null) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            String cpfFuncionario = Input.getString("Digite o CPF do funcionário: ");
            Employee funcionario = employeeService.findEmployeeByCpf(cpfFuncionario);
            if (funcionario == null) {
                System.out.println("Funcionário não encontrado!");
                return;
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
                return;
            }

            orderService.saveOrder(pedido);
            System.out.println("Venda realizada com sucesso!");
            pedido.print();

        } catch (Exception e) {
            System.out.println("Erro ao efetuar venda: " + e.getMessage());
        }
    }

    public void listSales() {
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
        } catch (ConnectionException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            System.out.println("Verifique se o banco de dados está em execução.");
        } catch (DatabaseException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
    }
} 