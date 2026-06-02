import org.junit.Test;
import static org.junit.Assert.*;

public class ValidadorArchivoTest {

    private final ValidadorArchivo validador = new ValidadorArchivo();

    @Test
    public void testArchivoValidoMenorA50MB() {
        // Escenario 1: Archivo real dentro de los límites
        Archivo archivo = new Archivo("practica_sistemas.pdf", 45.5, "C:/docs/practica.pdf");
        assertTrue(validador.esValido(archivo));
    }

    @Test
    public void testArchivoInvalidoPorTamano() {
        // Escenario 3: Rechazar archivos que superen los 50 MB
        Archivo archivo = new Archivo("proyecto_pesado.pdf", 51.2, "C:/docs/proyecto.pdf");
        assertFalse(validador.esValido(archivo));
    }

    @Test
    public void testArchivoInvalidoPorFormato() {
        // Escenario 2: Bloquear ejecutables para seguridad del sistema
        Archivo archivo = new Archivo("script_oculto.exe", 2.0, "C:/docs/script.exe");
        assertFalse(validador.esValido(archivo));
    }

    @Test
    public void testArchivoValidoExactamente50MB() {
        // Caso Borde: El sistema debe aceptar exactamente 50.0 MB
        Archivo archivo = new Archivo("documento_limite.docx", 50.0, "C:/docs/doc.docx");
        assertTrue(validador.esValido(archivo));
    }
}
