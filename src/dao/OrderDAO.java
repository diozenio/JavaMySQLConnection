package dao;

import domain.*;
import db.ConnectionHelper;
import exceptions.ConnectionException;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final ClientDAO clientDAO;
    private final EmployeeDAO employeeDAO;
    private final ProductDAO productDAO;

    public OrderDAO() {
        this.clientDAO = new ClientDAO();
        this.employeeDAO = new EmployeeDAO();
        this.productDAO = new ProductDAO();
    }

    public void save(Order order) {
        String sql = "INSERT INTO PEDIDO (CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL) VALUES (?, ?, ?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, order.getClient().getCpf());
            pst.setString(2, order.getEmployee().getCpf());
            pst.setDouble(3, order.getTotalValue());

            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            }

            saveOrderItems(order, connection);

            pst.close();
            connection.close();

        } catch (ConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao salvar venda no banco de dados", e);
        }
    }

    private void saveOrderItems(Order order, Connection connection) throws SQLException {
        String sql = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?, ?, ?, ?);";

        PreparedStatement pst = connection.prepareStatement(sql);
        for (OrderItem item : order.getItems()) {
            pst.setInt(1, order.getId());
            pst.setInt(2, item.getProduct().getID());
            pst.setInt(3, item.getQuantity());
            pst.setDouble(4, item.getValue());
            pst.executeUpdate();
        }
        pst.close();
    }

    public List<Order> findAll() {
        String sql = "SELECT * FROM PEDIDO;";
        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String clientCpf = rs.getString("CPF_CLIENTE_FK");
                String employeeCpf = rs.getString("CPF_FUNCIONARIO_FK");
                double totalValue = rs.getDouble("VALOR_TOTAL");

                Client client = clientDAO.findByCpf(clientCpf);
                Employee employee = employeeDAO.findByCpf(employeeCpf);

                Order order = new Order(id, client, employee, totalValue);
                loadOrderItems(order, connection);
                orders.add(order);
            }

            pst.close();
            connection.close();

        } catch (ConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar vendas no banco de dados", e);
        }

        return orders;
    }

    private void loadOrderItems(Order order, Connection connection) throws SQLException {
        String sql = "SELECT * FROM ITEM_PEDIDO WHERE ID_PEDIDO_FK = ?;";
        
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, order.getId());

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            int productId = rs.getInt("ID_PRODUTO_FK");
            int quantity = rs.getInt("QUANTIDADE");
            double value = rs.getDouble("VALOR");

            Product product = productDAO.findById(productId);
            OrderItem item = new OrderItem(id, product, quantity, value);
            order.getItems().add(item);
        }
        pst.close();
    }
} 