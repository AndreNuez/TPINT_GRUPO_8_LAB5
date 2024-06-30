package negocio;

import java.util.List;

import entidad.Usuario;

public interface IUsuarioNegocio {
	
	public boolean Add(Usuario usuario);
	public List<Usuario> ReadAll();
	public Usuario getUsuarioDB(Usuario usuario);
	public Usuario getPerfilInvitado(Usuario usuario);
}
