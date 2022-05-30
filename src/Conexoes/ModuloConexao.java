package Conexoes;

import java.sql.*;

public class ModuloConexao {

    public static Connection conector() {
        java.sql.Connection conexao = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3307/petland1";
        String user = "root";
        String senha = "0302";

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, senha);
            return conexao;
        } catch (Exception e) {
            return null;
        }

    }

}
