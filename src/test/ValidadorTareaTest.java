import org.junit.Test;
import static org.junit.Assert.*;

public class ValidadorTareaTest {

    private final ValidadorTarea validador = new ValidadorTarea();

    @Test
    public void testTareaConTituloValido() {
        // Escenario 1: Tarea correcta con prioridad definida
        Tarea tarea = new Tarea("Diseñar interfaz principal", Prioridad.ALTA);
        assertTrue(validador.procesarTarea(tarea));
    }

    @Test
    public void testTareaRechazadaPorTituloVacio() {
        // Escenario 3: Bloquear tareas que no tengan descripción
        Tarea tarea = new Tarea("", Prioridad.BAJA);
        assertFalse(validador.procesarTarea(tarea));
    }

    @Test
    public void testAsignacionPrioridadPorDefecto() {
        // Escenario 2: Si la interfaz envía un nulo, el validador debe asignar MEDIA
        Tarea tarea = new Tarea("Configurar servidor SQL", null);
        validador.procesarTarea(tarea); // El método procesar debe ajustar el valor interno
        
        assertEquals(Prioridad.MEDIA, tarea.getPrioridad());
    }
}
