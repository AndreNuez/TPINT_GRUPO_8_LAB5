package entidad;

public enum EstadoTurno {
	
	PENDIENTE("Pendiente"),
    PRESENTE("Presente"),
    AUSENTE("Ausente");

    private final String estado;

    EstadoTurno(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
