import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TareaDAO {

    public boolean guardarTarea(Tarea tarea) {
        String sql = "INSERT INTO Tareas_Pendientes (titulo, prioridad) VALUES (?, ?)";
        
        try (Connection con = ConexionSQL.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            if (con == null) return false; 
            
            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getPrioridad().name()); // Extrae "ALTA", "MEDIA" o "BAJA"
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
