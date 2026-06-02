import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BDAcademica;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa"; 
    private static final String PASS = "guitarra_500!"; 

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Error crítico de conexión a SQL Server: " + e.getMessage());
            return null;
        }
    }
}
