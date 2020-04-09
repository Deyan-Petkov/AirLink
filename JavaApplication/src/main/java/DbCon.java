
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author deyan
 */
public class DbCon {

    //path to the database file
    static String SQCONN = "jdbc:sqlite:AirLink.db";

    public static Connection getConnection() throws ClassNotFoundException {
        try {
            // return DriverManager.getConnection(SQCONN);//Allocates the appropriate driver for the database and creates connection
            Connection con = DriverManager.getConnection(SQCONN);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            return con;

        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
