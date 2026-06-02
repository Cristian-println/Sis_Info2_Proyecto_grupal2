import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class DashboardAcademico extends JFrame {

    private JPanel panelCentral;
    private CardLayout cardLayout;

    private JTextField txtRutaArchivo;
    private JLabel lblDetalleArchivo, lblEstadoSubida;
    private JButton btnSeleccionar, btnEnviarTarea;
    private File archivoFisico;

    private JTextField txtTituloTarea;
    private JComboBox<String> cbxPrioridad;
    private JButton btnGuardarTarea;
    private JLabel lblEstadoTarea;

    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;

    public DashboardAcademico() {
        configurarVentanaPrincipal();
        inicializarUI();
    }

    private void configurarVentanaPrincipal() {
        setTitle("SGA - Sistema de Gestión Académica");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
        catch (Exception ignored) {}
    }

    private void inicializarUI() {

        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBackground(new Color(33, 47, 61)); // Azul oscuro elegante
        menuLateral.setPreferredSize(new Dimension(220, 0));
        menuLateral.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel lblLogo = new JLabel("Menú Principal");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(lblLogo);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 40)));

        JButton btnNavSubir = crearBotonMenu("1. Entregar Tarea");
        JButton btnNavCrear = crearBotonMenu("2. Clasificar Tarea");
        JButton btnNavBD = crearBotonMenu("3. Historial BD");

        menuLateral.add(btnNavSubir);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnNavCrear);
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnNavBD);

        add(menuLateral, BorderLayout.WEST);

        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);
        panelCentral.setBackground(new Color(245, 245, 245));

        panelCentral.add(crearPanelSubida(), "PanelSubida");
        panelCentral.add(crearPanelPrioridad(), "PanelPrioridad");
        panelCentral.add(crearPanelBaseDatos(), "PanelBD");

        add(panelCentral, BorderLayout.CENTER);

        btnNavSubir.addActionListener(e -> cardLayout.show(panelCentral, "PanelSubida"));
        btnNavCrear.addActionListener(e -> cardLayout.show(panelCentral, "PanelPrioridad"));
        btnNavBD.addActionListener(e -> cardLayout.show(panelCentral, "PanelBD"));
    }

    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
       
        boton.setMaximumSize(new Dimension(180, 40)); 
        boton.setBackground(new Color(40, 116, 166));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        return boton;
    }

    private JPanel crearPanelSubida() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Módulo de Entrega de Archivos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBounds(40, 30, 400, 30);
        panel.add(titulo);

        txtRutaArchivo = new JTextField("Esperando archivo físico...");
        txtRutaArchivo.setEditable(false);
        txtRutaArchivo.setBounds(40, 100, 380, 35);
        panel.add(txtRutaArchivo);

        btnSeleccionar = new JButton("Explorar...");
        btnSeleccionar.setBackground(Color.LIGHT_GRAY);
        btnSeleccionar.setBounds(440, 100, 120, 35);
        panel.add(btnSeleccionar);

        lblDetalleArchivo = new JLabel("Peso estimado: 0.0 MB");
        lblDetalleArchivo.setBounds(40, 140, 300, 20);
        panel.add(lblDetalleArchivo);

        btnEnviarTarea = new JButton("Validar y Enviar");
        btnEnviarTarea.setBackground(new Color(39, 174, 96)); // Verde éxito
        btnEnviarTarea.setForeground(Color.WHITE);
        btnEnviarTarea.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEnviarTarea.setBounds(40, 200, 180, 40);
        panel.add(btnEnviarTarea);

        lblEstadoSubida = new JLabel("");
        lblEstadoSubida.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstadoSubida.setBounds(40, 260, 500, 25);
        panel.add(lblEstadoSubida);

        btnSeleccionar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                archivoFisico = chooser.getSelectedFile();
                txtRutaArchivo.setText(archivoFisico.getAbsolutePath());
                lblDetalleArchivo.setText(String.format("Peso real: %.2f MB", archivoFisico.length() / 1048576.0));
                lblEstadoSubida.setText("");
            }
        });

        return panel;
    }

    private JPanel crearPanelPrioridad() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Registro de Tareas Pendientes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBounds(40, 30, 400, 30);
        panel.add(titulo);

        JLabel lblNom = new JLabel("Descripción de la Tarea:");
        lblNom.setBounds(40, 90, 200, 20);
        panel.add(lblNom);

        txtTituloTarea = new JTextField();
        txtTituloTarea.setBounds(40, 115, 380, 35);
        panel.add(txtTituloTarea);

        JLabel lblPri = new JLabel("Nivel de Urgencia:");
        lblPri.setBounds(440, 90, 150, 20);
        panel.add(lblPri);

        cbxPrioridad = new JComboBox<>(new String[]{"ALTA", "MEDIA", "BAJA"});
        cbxPrioridad.setSelectedIndex(1);
        cbxPrioridad.setBounds(440, 115, 120, 35);
        panel.add(cbxPrioridad);

        btnGuardarTarea = new JButton("Guardar en Sistema");
        btnGuardarTarea.setBackground(new Color(41, 128, 185)); // Azul
        btnGuardarTarea.setForeground(Color.WHITE);
        btnGuardarTarea.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardarTarea.setBounds(40, 190, 180, 40);
        panel.add(btnGuardarTarea);

        lblEstadoTarea = new JLabel("");
        lblEstadoTarea.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstadoTarea.setBounds(40, 250, 500, 25);
        panel.add(lblEstadoTarea);

        return panel;
    }

    private JPanel crearPanelBaseDatos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel titulo = new JLabel("Historial de Entregas (Base de Datos SQL)");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre Archivo / Tarea", "Detalle", "Fecha de Registro"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaHistorial = new JTable(modeloTabla);
        tablaHistorial.setRowHeight(25);
        tablaHistorial.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public File getArchivoFisico() { return archivoFisico; }
    public JButton getBtnEnviarTarea() { return btnEnviarTarea; }
    public String getTituloTarea() { return txtTituloTarea.getText(); }
    public String getPrioridadSeleccionada() { return (String) cbxPrioridad.getSelectedItem(); }
    public JButton getBtnGuardarTarea() { return btnGuardarTarea; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; } 

    public void mostrarAlerta(String mensaje, boolean exito, int tipoPantalla) {
        JLabel etiquetaDestino = (tipoPantalla == 1) ? lblEstadoSubida : lblEstadoTarea;
        etiquetaDestino.setText(mensaje);
        etiquetaDestino.setForeground(exito ? new Color(39, 174, 96) : Color.RED);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardAcademico().setVisible(true));
    }
}
