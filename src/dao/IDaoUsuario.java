package dao;

import java.util.List;

import entidad.Usuario;

public interface IDaoUsuario {
	
	public boolean Add(Usuario usuario);
	public List<Usuario> ReadAll();
	public Usuario getUserById(int idUser);
	public boolean Delete(Usuario user);

}
