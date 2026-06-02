public class ValidadorTarea {

    public boolean procesarTarea(Tarea tarea) {
        if (!tieneTituloValido(tarea.getTitulo())) {
            return false;
        }
        
        asignarPrioridadPorDefecto(tarea);
        return true;
    }

    private boolean tieneTituloValido(String titulo) {
        return titulo != null && !titulo.trim().isEmpty();
    }

    private void asignarPrioridadPorDefecto(Tarea tarea) {
        if (tarea.getPrioridad() == null) {
            tarea.setPrioridad(Prioridad.MEDIA);
        }
    }
}
