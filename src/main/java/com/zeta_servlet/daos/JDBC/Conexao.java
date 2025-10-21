package com.zeta_servlet.daos.JDBC;

import com.zeta_servlet.ExceptionHandler.ExceptionHandler;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexao {

    public Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
<<<<<<< HEAD
            Dotenv dotenv = Dotenv.load();

=======
            Dotenv dotenv = Dotenv.configure().load();
>>>>>>> 89a23e2a2d306fa3edcb8a6b5149f0cf4e0c5c86

            String url_db = dotenv.get("URL_DB");
            String user_db = dotenv.get("USER_DB");
            String password_db = dotenv.get("PASSWORD_DB");
<<<<<<< HEAD
            conn = DriverManager.getConnection(
                    url_db,
                    user_db, password_db);
=======
            conn = DriverManager.getConnection(url_db, user_db, password_db);
>>>>>>> 89a23e2a2d306fa3edcb8a6b5149f0cf4e0c5c86
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }finally {
            return conn;
        }
    }

    public void desconectar(Connection conn){
        try {


            if (conn != null &&  !conn.isClosed()){
                conn.close();}
        }catch (Exception e){
            ExceptionHandler eh = new ExceptionHandler(e);
            eh.printExeption();
        }
    }
}
