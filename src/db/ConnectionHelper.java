package db;

import exceptions.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    public static Connection getConnection() throws SQLException {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost/LOJA";
        final String user = "root";
        final String password = "root";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Driver MySQL não encontrado", e);
        } catch (SQLException e) {
            if (e.getMessage().contains("Communications link failure")) {
                throw new ConnectionException("Não foi possível conectar ao banco de dados. Verifique se o MySQL está em execução.", e);
            }
            throw new ConnectionException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }
}