public class ValidadorArchivo {
    private static final double LIMITE_MB = 50.0;

    public boolean esValido(Archivo archivo) {
       
        return esTamanoValido(archivo.getTamanoMB()) && esExtensionValida(archivo.getNombreReal());
    }

    private boolean esTamanoValido(double tamanoMB) {
        return tamanoMB <= LIMITE_MB;
    }

    private boolean esExtensionValida(String nombreReal) {
        String nombre = nombreReal.toLowerCase();
        return nombre.endsWith(".pdf") || nombre.endsWith(".doc") || nombre.endsWith(".docx");
    }
}
