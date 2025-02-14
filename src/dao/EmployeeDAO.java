package dao;

import domain.Employee;
import db.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public Employee findByCpf(String cpf) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE CPF = ?;";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("NOME");
                String address = rs.getString("ENDERECO");
                String phone = rs.getString("TELEFONE");

                return new Employee(cpf, name, address, phone);
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Employee> findAll() {
        String sql = "SELECT * FROM FUNCIONARIO;";
        List<Employee> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String name = rs.getString("NOME");
                String address = rs.getString("ENDERECO");
                String phone = rs.getString("TELEFONE");

                Employee employee = new Employee(cpf, name, address, phone);
                lista.add(employee);
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
} 