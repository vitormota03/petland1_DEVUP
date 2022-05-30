package ClassePrincipal;

import conexoes.MySQL;
import Telas.LoginFuncionario;
import java.sql.Connection;
import java.sql.SQLException;

public class petland {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;

        LoginFuncionario login = new LoginFuncionario();
        login.setVisible(true);

        MySQL novacon = new MySQL();
        novacon.conectaBanco();

    }

}
