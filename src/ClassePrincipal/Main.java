package classePrincipal;

import conexoes.MySQL;
import telas.Login;
import java.sql.Connection;
import telas.CaixaON;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;

        MySQL novacon = new MySQL();
        novacon.conectaBanco();

        Login login = new Login();
        login.setVisible(true);
        
     

    }

}
