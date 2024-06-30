package entidad;

public enum PerfilUsuario {
	
	INVALIDO(0),
	ADMINISTRADOR(1), 
	MEDICO(2), 
	INVITADO(3);
	
	private final int idPerfil;
	
	PerfilUsuario(int id) {
		this.idPerfil = id;
	}
	
	public int getPerfilUsuario() {
		return this.idPerfil;
	}

}
