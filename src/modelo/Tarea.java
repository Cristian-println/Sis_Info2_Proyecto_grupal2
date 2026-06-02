public class Tarea {
    private String titulo;
    private Prioridad prioridad;

    public Tarea(String titulo, Prioridad prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    public String getTitulo() { return titulo; }
    
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
}
