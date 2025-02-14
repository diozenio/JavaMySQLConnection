package dao;

import domain.Client;
import db.ConnectionHelper;
import exceptions.DatabaseException;
import exceptions.NotFoundException;
import exceptions.ConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    public void save(Client client) {
        String sql = "INSERT INTO CLIENTE (CPF, NOME, ENDERECO, TELEFONE) VALUES (?, ?, ?, ?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, client.getCpf());
            pst.setString(2, client.getName());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getPhone());

            pst.executeUpdate();
            pst.close();
            connection.close();

        } catch (ConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao salvar cliente no banco de dados", e);
        }
    }

    public List<Client> findAll() {
        String sql = "SELECT * FROM CLIENTE;";
        List<Client> lista = new ArrayList<>();

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String name = rs.getString("NOME");
                String address = rs.getString("ENDERECO");
                String phone = rs.getString("TELEFONE");

                Client client = new Client(cpf, name, address, phone);
                lista.add(client);
            }

            pst.close();
            connection.close();

        } catch (ConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar clientes no banco de dados", e);
        }

        return lista;
    }

    public Client findByCpf(String cpf) {
        String sql = "SELECT * FROM CLIENTE WHERE CPF = ?;";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("NOME");
                String address = rs.getString("ENDERECO");
                String phone = rs.getString("TELEFONE");

                return new Client(cpf, name, address, phone);
            }

            pst.close();
            connection.close();

            throw new NotFoundException("Cliente não encontrado com CPF: " + cpf);

        } catch (ConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar cliente no banco de dados", e);
        }
    }
} 