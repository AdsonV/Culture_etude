package c_e;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    static Connection conn;
    static Connection conn2;
    static Connection conn3;
    public static void conectar()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/culture_etude","usuario","usuario0123");
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void conectar2()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/culture_etude","administrador","admin0123");
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void conectar3()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/culture_etude","root","adsonsql");
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void desconectar2(){
        try {
            conn2.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void desconectar3(){
        try {
            conn3.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}