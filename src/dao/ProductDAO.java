package dao;

import domain.Product;
import db.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public void save(Product product) {
        String sql = "INSERT INTO PRODUTO (NOME, VALOR_UNIT, QUANTIDADE) VALUES (?, ?, ?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, product.getName());
            pst.setDouble(2, product.getPrice());
            pst.setInt(3, product.getQuantity());

            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setID(generatedKeys.getInt(1));
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM PRODUTO;";
        List<Product> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NOME");
                double price = rs.getDouble("VALOR_UNIT");
                int quantity = rs.getInt("QUANTIDADE");

                Product product = new Product(id, name, price, quantity);
                lista.add(product);
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
