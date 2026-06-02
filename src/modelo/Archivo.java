public class Archivo {
    private String nombreReal;
    private double tamanoMB;
    private String rutaLocal;

    public Archivo(String nombreReal, double tamanoMB, String rutaLocal) {
        this.nombreReal = nombreReal;
        this.tamanoMB = tamanoMB;
        this.rutaLocal = rutaLocal;
    }

    public String getNombreReal() { return nombreReal; }
    public double getTamanoMB() { return tamanoMB; }
    public String getRutaLocal() { return rutaLocal; }
}
