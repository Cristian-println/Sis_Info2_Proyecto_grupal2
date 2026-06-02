import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArchivoDAO {
    
    public boolean guardarArchivo(Archivo archivo) {
        String sql = "INSERT INTO Archivos_Entregados (nombre_real, tamano_mb, ruta_local) VALUES (?, ?, ?)";
        
        try (Connection con = ConexionSQL.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            if (con == null) return false; 
            
            ps.setString(1, archivo.getNombreReal());
            ps.setDouble(2, archivo.getTamanoMB());
            ps.setString(3, archivo.getRutaLocal());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
