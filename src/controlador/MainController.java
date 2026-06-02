import javax.swing.table.DefaultTableModel;

public class MainController {
    private DashboardAcademico vista;
    private ValidadorArchivo validadorArchivo = new ValidadorArchivo();
    private ArchivoDAO archivoDAO = new ArchivoDAO();
    private ValidadorTarea validadorTarea = new ValidadorTarea();
    private TareaDAO tareaDAO = new TareaDAO();

    public MainController(DashboardAcademico vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        
        vista.getBtnEnviarTarea().addActionListener(e -> {
            if (vista.getArchivoFisico() != null) {
                double mb = vista.getArchivoFisico().length() / 1048576.0;
                Archivo archivo = new Archivo(vista.getArchivoFisico().getName(), mb, vista.getArchivoFisico().getAbsolutePath());
                
                if (validadorArchivo.esValido(archivo)) {
                    if (archivoDAO.guardarArchivo(archivo)) {
                        vista.mostrarAlerta("¡Archivo guardado en BD con éxito!", true, 1);
                        actualizarTabla("Archivo: " + archivo.getNombreReal(), mb + " MB");
                    } else {
                        vista.mostrarAlerta("Error al guardar en SQL Server.", false, 1);
                    }
                } else {
                    vista.mostrarAlerta("Error: Formato inválido o supera los 50MB.", false, 1);
                }
            }
        });

        vista.getBtnGuardarTarea().addActionListener(e -> {
            Prioridad p = Prioridad.valueOf(vista.getPrioridadSeleccionada());
            Tarea tarea = new Tarea(vista.getTituloTarea(), p);
            
            if (validadorTarea.procesarTarea(tarea)) {
                if (tareaDAO.guardarTarea(tarea)) {
                    vista.mostrarAlerta("¡Tarea registrada en BD con éxito!", true, 2);
                    actualizarTabla("Tarea: " + tarea.getTitulo(), "Prioridad " + p.name());
                } else {
                    vista.mostrarAlerta("Error al conectar con SQL Server.", false, 2);
                }
            } else {
                vista.mostrarAlerta("Error: El título no puede estar vacío.", false, 2);
            }
        });
    }

    private void actualizarTabla(String nombre, String detalle) {
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.addRow(new Object[]{modelo.getRowCount() + 1, nombre, detalle, "Hoy"});
    }

    public static void main(String[] args) {
        new MainController(new DashboardAcademico()).vista.setVisible(true);
    }
}
