package fr.esiee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    public static void main(String[] args) {
        String urlLocal = "jdbc:mariadb://localhost:3306/EasyTrain";
        String userLocal = "root";
        String pwdLocal = "";
        String urlDistant = "jdbc:mariadb://localhost:3306/EasyTrain";
        String userDistant = "root";
        String pwdDistant = "";
/// Création d'une connexion a la bdd
        try {
            Connection connection = DriverManager.getConnection(
                    urlLocal, userLocal, pwdLocal);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
